package py.com.pgimenez.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import py.com.pgimenez.config.server.util.DotEnvLoader;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		DotEnvLoader.loadIntoSystemProperties(); // Cargar las variables del archivo .env en las propiedades del sistema
		SpringApplication.run(Application.class, args);
	}

}
