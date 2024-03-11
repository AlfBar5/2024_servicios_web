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
	@Bean
	@LoadBalanced
	public RestClient.Builder getBuilder() {
		return RestClient.builder();
	}
	
	
	@Bean
	public RestClient getClient(RestClient.Builder builder) {
		return builder.build();
	} 
	
}
