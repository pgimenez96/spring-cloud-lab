package py.com.pgimenez.report.ms.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import py.com.pgimenez.report.ms.model.Company;

@Repository
@Slf4j
public class CompanyFallbackRepository {

    private final WebClient webClient;
    private final String uri;

    public CompanyFallbackRepository(WebClient webClient, @Value("${fallback.uri}") String uri) {
        this.webClient = webClient;
        this.uri = uri;
    }

    public Company getByName(String name) {
        log.warn("Calling companies fallback {}", uri);
        return this.webClient
                .get()
                .uri(uri, name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Company.class)
                .log()
                .block();
    }

}
