package py.com.pgimenez.companies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.pgimenez.companies.entities.WebSite;

public interface WebSideRepository extends JpaRepository<WebSite, Long> {
}
