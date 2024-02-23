package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	//La anotación @Bean hace que Spring pueda instanciar estos métodos configuración por defecto
	//accedemos con el LoadBalanced al servicio Eureka de productos
	@Bean
	@LoadBalanced
	public RestClient.Builder getBuilder() {
		return RestClient.builder();
	}
	
	//La anotación @Bean hace que Spring pueda instanciar estos métodos configuración por defecto
	@Bean
	public RestClient getClient(RestClient.Builder builder) {
		return builder.build();
	}
	
	//cuando tenemos el error de "host desconido" es que está intentando acceder fuera de eureka, 
	//está intentando acceder al nombre que hemos puesto sin pasar por eureka
	
}
