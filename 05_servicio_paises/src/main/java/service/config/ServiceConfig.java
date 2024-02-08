package service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;



//Le decimos en la primera anotación en qué paquetes están las clases que tiene que instanciar
//En la segunda anotación, le decimos a spring que estamos ante una clase de configuración

@ComponentScan(basePackages = {"service.implementation"})
@Configuration
public class ServiceConfig {
	
	//La anotación @Bean hace que Spring pueda instanciar estos métodos configuración por defecto
	@Bean
	public RestClient getClient() {
		return RestClient.create();
	}

	
}
