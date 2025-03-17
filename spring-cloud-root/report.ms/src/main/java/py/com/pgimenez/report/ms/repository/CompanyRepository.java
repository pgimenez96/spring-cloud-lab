package py.com.pgimenez.report.ms.repository;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import py.com.pgimenez.report.ms.bean.LoadBalanceConfig;
import py.com.pgimenez.report.ms.model.Company;

import java.util.Optional;

@FeignClient(name = "companies")
@LoadBalancerClient(name = "companies", configuration = LoadBalanceConfig.class)
public interface CompanyRepository {

    @GetMapping(path = "/companies/company/{name}")
    Optional<Company> getByName(@PathVariable String name);

    @PostMapping(path = "/companies/company")
    Optional<Company> post(@RequestBody Company company);

    @DeleteMapping(path = "/companies/company/{name}")
    void deleteByName(@PathVariable String name);

}
