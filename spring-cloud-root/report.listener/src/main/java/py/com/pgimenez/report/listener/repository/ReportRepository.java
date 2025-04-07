package py.com.pgimenez.report.listener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import py.com.pgimenez.report.listener.document.ReportDocument;

public interface ReportRepository extends MongoRepository<ReportDocument, String> {
}
