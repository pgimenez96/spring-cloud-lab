package py.com.pgimenez.companies.services;

import py.com.pgimenez.companies.entities.Company;

public interface CompanyService {

    Company create(Company company);

    Company readByName(String name);

    Company update(Company company, String name);

    void delete(String name);

}
