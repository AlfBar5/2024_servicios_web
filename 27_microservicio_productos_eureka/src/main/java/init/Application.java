package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestClient;


@EnableJpaRepositories
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	//La anotación @Bean hace que Spring pueda instanciar estos métodos configuración por defecto
	@Bean
	public RestClient getClient() {
		return RestClient.create();
	}
	
}
