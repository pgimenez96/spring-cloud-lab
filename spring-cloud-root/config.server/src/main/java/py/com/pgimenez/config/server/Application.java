package py.com.pgimenez.config.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

import py.com.pgimenez.config.server.config.EarlyProfileInitializer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class Application {

	@Value("${spring.profiles.active}")
	private String profile;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.addListeners(new EarlyProfileInitializer());
		app.run(args);
	}

}
