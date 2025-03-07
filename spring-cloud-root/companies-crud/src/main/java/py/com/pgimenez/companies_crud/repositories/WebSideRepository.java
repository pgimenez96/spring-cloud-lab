package py.com.pgimenez.companies_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.pgimenez.companies_crud.entities.WebSite;

public interface WebSideRepository extends JpaRepository<WebSite, Long> {
}
