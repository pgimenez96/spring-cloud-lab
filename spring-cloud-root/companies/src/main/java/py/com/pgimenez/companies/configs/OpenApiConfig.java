package py.com.pgimenez.companies.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI buildOpenApi(ServletContext servletContext) {
                return new OpenAPI()
                        .addServersItem(new Server().url(servletContext.getContextPath()))
                        .info(new Info()
                                .title("Companies CRUD")
                                .description("This is a CRUD for management companies")
                                .version("1.0.0")
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                                .description("")
                                .url(""));
        }

}
