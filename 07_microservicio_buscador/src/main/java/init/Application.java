package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//le decimos a SpringBoot los paquetes que tiene que escanear
@SpringBootApplication(scanBasePackages = {"controller","service.implementation"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
