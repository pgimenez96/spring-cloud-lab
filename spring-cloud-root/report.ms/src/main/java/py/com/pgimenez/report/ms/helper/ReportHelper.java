package py.com.pgimenez.report.ms.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import py.com.pgimenez.report.ms.model.Company;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ReportHelper {

    @Value("${report.template}")
    private String reportTemplate;

    public String readTemplate(Company company) {

        return this.reportTemplate
                .replace("{company}", company.getName())
                .replace("{foundation_date}", company.getFoundationDate().toString())
                .replace("{founder}", company.getFounder())
                .replace("{web_sites}", company.getWebSites().toString());
    }

    public List<String> getPlaceholdersFormTemplate(String template) {
        var split = template.split("\\{");
        return Arrays.stream(split)
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    var index = line.indexOf("}");
                    return line.substring(0, index);
                })
                .toList();
    }

}
