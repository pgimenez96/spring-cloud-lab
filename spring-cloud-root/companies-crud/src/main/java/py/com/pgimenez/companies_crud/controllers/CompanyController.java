package py.com.pgimenez.companies_crud.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.pgimenez.companies_crud.entities.Company;
import py.com.pgimenez.companies_crud.services.CompanyService;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
@Tag(name = "Companies resources")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "get a company given a company name")
    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.debug("GET company {}", name);
        return ResponseEntity.ok(this.companyService.readByName(name));
    }

    @Operation(summary = "save in DB a company given a company from body")
    @PostMapping
    public ResponseEntity<Company> post(@RequestBody Company company) {
        log.debug("POST company {}", company);
        return ResponseEntity.created(
                URI.create(this.companyService.create(company).getName()))
                .build();
    }

    @Operation(summary = "update in DB a company given a company from body")
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(@PathVariable String name,
                                       @RequestBody Company company) {
        log.debug("PUT company {}", company);
        return ResponseEntity.ok(this.companyService.update(company, name));
    }

    @Operation(summary = "delete in DB a company given a company name")
    @DeleteMapping(path = "{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        log.debug("DELETE company {}", name);
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }

}
