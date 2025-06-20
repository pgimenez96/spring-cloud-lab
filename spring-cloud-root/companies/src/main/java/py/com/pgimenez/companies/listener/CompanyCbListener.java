package py.com.pgimenez.companies.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import py.com.pgimenez.companies.entities.Company;
import py.com.pgimenez.companies.services.CompanyService;

@Slf4j
@AllArgsConstructor
@Component
public class CompanyCbListener {

    private final CompanyService companyService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "consumerCbReport", groupId = "circuitbreaker")
    public void insertMsgEvent(String companyEvent) throws JsonProcessingException {
        log.info("Received event circuit breaker {}", companyEvent);
        var company = this.objectMapper.readValue(companyEvent, Company.class);
        this.companyService.create(company);
    }

}
