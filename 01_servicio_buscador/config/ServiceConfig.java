package service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



//Le decimos en la primera anotación en qué paquetes están las clases que tiene que instanciar
//En la segunda anotación, le decimos a spring que estamos ante una clase de configuración

@ComponentScan(basePackages = {"service.implementation"})
@Configuration
public class ServiceConfig {

	
}
