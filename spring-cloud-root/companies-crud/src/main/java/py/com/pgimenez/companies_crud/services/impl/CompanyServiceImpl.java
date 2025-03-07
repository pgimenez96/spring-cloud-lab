package py.com.pgimenez.companies_crud.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import py.com.pgimenez.companies_crud.constants.CompanyConstants;
import py.com.pgimenez.companies_crud.entities.Category;
import py.com.pgimenez.companies_crud.entities.Company;
import py.com.pgimenez.companies_crud.repositories.CompanyRepository;
import py.com.pgimenez.companies_crud.services.CompanyService;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(webSite -> {
            if (Objects.isNull(webSite.getCategory())) {
                webSite.setCategory(Category.NONE);
            }
        });
        return this.companyRepository.save(company);
    }

    @Override
    public Company readByName(String name) {
        return this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(CompanyConstants.COMPANY_NOT_FOUND_MSG));
    }

    @Override
    public Company update(Company company, String name) {
        var companyToUpdate = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(CompanyConstants.COMPANY_NOT_FOUND_MSG));
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFounder(company.getFounder());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        return this.companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        var companyToDelete = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(CompanyConstants.COMPANY_NOT_FOUND_MSG));
        this.companyRepository.delete(companyToDelete);
    }

}
