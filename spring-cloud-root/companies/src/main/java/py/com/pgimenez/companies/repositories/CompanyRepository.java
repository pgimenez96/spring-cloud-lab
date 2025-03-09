package py.com.pgimenez.companies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.pgimenez.companies.entities.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

}
