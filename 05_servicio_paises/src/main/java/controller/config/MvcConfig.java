package controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//Paquete con clases a instanciar

@ComponentScan(basePackages= {"controller"})
@EnableWebMvc
@Configuration
public class MvcConfig {
	
		
	
}



