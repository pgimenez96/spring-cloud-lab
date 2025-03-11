package py.com.pgimenez.companies.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.util.jar.Manifest;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("py.com.pgimenez")
@ServletComponentScan("py.com.pgimenez")
public class AppWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private CustomInterceptor customInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor); // Registra el interceptor personalizado
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    @Lazy
    public Manifest manifest() throws IOException {
        final Resource resource = new ClassPathResource("META-INF/MANIFEST.MF"); // Carga el archivo MANIFEST.MF desde el classpath
        return new Manifest(resource.getInputStream());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html"); // Redirige la ruta raíz a Swagger UI
    }

}