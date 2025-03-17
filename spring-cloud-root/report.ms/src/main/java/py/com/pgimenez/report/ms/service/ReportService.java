package py.com.pgimenez.report.ms.service;

public interface ReportService {

    String makeReport(String name);

    String saveReport(String report);

    void deleteReport(String name);

}
