package py.com.pgimenez.gateway.bean;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import py.com.pgimenez.gateway.filter.AuthFilter;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class GatewayBeans {

    private final AuthFilter authFilter;

    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies/company/*")
                        .uri("http://localhost:8081")
                )
                .route(route -> route
                        .path("/report.ms/report/*")
                        .uri("http://localhost:7070")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies/company/**")
                        .uri("lb://companies") // info: En load balance se debe indicar el nombre del componente, ej.: lb://component-name
                )
                .route(route -> route
                        .path("/report.ms/report/**")
                        .uri("lb://report.ms")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on-cb")
    public RouteLocator routeLocatorEurekaOnCB(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies/company/**")
                        .filters(filter -> {
                            filter.circuitBreaker(config -> config
                                    .setName("gateway-cb")
                                    .setStatusCodes(Set.of("400", "500"))
                                    .setFallbackUri("forward:/companies.fallback/company/*")
                            );
                            return filter;
                        })
                        .uri("lb://companies")
                )
                .route(route -> route
                        .path("/report.ms/report/**")
                        .uri("lb://report.ms")
                )
                .route(route -> route
                        .path("/companies.fallback/company/**")
                        .uri("lb://companies.fallback")
                )
                .build();
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routeLocatorOAuth2(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies/company/**")
                        .filters(filter -> {
                            filter.circuitBreaker(config -> config
                                    .setName("gateway-cb")
                                    .setStatusCodes(Set.of("400", "500"))
                                    .setFallbackUri("forward:/companies.fallback/company/*")
                            );
                            filter.filter(this.authFilter);
                            return filter;
                        })
                        .uri("lb://companies")
                )
                .route(route -> route
                        .path("/report.ms/report/**")
                        .filters(filter -> filter.filter(this.authFilter))
                        .uri("lb://report.ms")
                )
                .route(route -> route
                        .path("/companies.fallback/company/**")
                        .filters(filter -> filter.filter(this.authFilter))
                        .uri("lb://companies.fallback")
                )
                .route(route -> route
                        .path("/auth.server/auth/**")
                        .uri("lb://auth.server")
                )
                .build();
    }

}
