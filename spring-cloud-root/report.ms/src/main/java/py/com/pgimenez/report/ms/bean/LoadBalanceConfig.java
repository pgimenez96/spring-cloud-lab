package py.com.pgimenez.report.ms.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
public class LoadBalanceConfig {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        log.info("Load balance config");
        return ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient()
                .build(context);
    }

}
