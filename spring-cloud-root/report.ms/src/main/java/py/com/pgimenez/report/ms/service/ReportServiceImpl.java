package py.com.pgimenez.report.ms.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import py.com.pgimenez.report.ms.helper.ReportHelper;
import py.com.pgimenez.report.ms.model.Company;
import py.com.pgimenez.report.ms.model.WebSite;
import py.com.pgimenez.report.ms.repository.CompanyFallbackRepository;
import py.com.pgimenez.report.ms.repository.CompanyRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final ReportHelper reportHelper;
    private final CompanyRepository companyRepository;
    private final CompanyFallbackRepository companyFallbackRepository;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    @Override
    public String makeReport(String name) {
        var circuitBreaker = this.circuitBreakerFactory.create("companies-circuitbreaker");
        return circuitBreaker.run(
                () -> this.makeReportMain(name),
                throwable -> this.makeReportFallback(name, throwable)
        );
    }

    @Override
    public String saveReport(String report) {
        var dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var placeholders = this.reportHelper.getPlaceholdersFormTemplate(report);
        var webSites = Stream.of(placeholders.get(3))
                .map(webSite -> WebSite.builder().name(webSite).build())
                .toList();

        var companyReq = Company.builder()
                .name(placeholders.get(0))
                .foundationDate(LocalDate.parse(placeholders.get(1), dateFormat))
                .founder(placeholders.get(2))
                .webSites(webSites)
                .build();

        var companyRes = this.companyRepository.post(companyReq)
                .orElseThrow();

        return companyRes.getName().concat(" company report saved");
    }

    @Override
    public void deleteReport(String name) {
        this.companyRepository.deleteByName(name);
    }

    private String makeReportMain(String name) {
        return reportHelper.readTemplate(this.companyRepository.getByName(name).orElseThrow());
    }

    private String makeReportFallback(String name, Throwable error) {
        log.warn(error.getMessage());
        return reportHelper.readTemplate(this.companyFallbackRepository.getByName(name));
    }

}
