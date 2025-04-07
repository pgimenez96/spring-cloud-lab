package py.com.pgimenez.report.listener.stream;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import py.com.pgimenez.report.listener.document.ReportDocument;
import py.com.pgimenez.report.listener.repository.ReportRepository;

import java.util.function.Consumer;

@Configuration
@Slf4j
@AllArgsConstructor
public class ReportListener {

    private ReportRepository reportRepository;

    @Bean
    public Consumer<String> consumerReport() {
        return report -> {
            this.reportRepository.save(ReportDocument.builder().content(report).build());
            log.info("Consuming report: {}", report);
        };
    }

}
