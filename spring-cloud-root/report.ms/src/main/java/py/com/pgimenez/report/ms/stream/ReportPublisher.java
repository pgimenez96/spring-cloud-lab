package py.com.pgimenez.report.ms.stream;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import py.com.pgimenez.report.ms.model.Company;

@Component
@AllArgsConstructor
public class ReportPublisher {

    private final StreamBridge streamBridge;

    public void publishReport(String report) {
        this.streamBridge.send("consumerReport", report);
        this.streamBridge.send("consumerReport-in-0", report);
        this.streamBridge.send("consumerReport-out-0", report);
    }

    public Company publishCbReport(String company) {
        this.streamBridge.send("consumerCbReport", company);
        this.streamBridge.send("consumerCbReport-in-0", company);
        this.streamBridge.send("consumerCbReport-out-0", company);
        return Company.builder().build();
    }

}
