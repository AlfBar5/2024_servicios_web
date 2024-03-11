package init.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import model.Resultado;

//anotación para que spring cree objetos que no pertenecen a una capa concreta
@Component
public class TestBuscador implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// Es como una especie de main
		//cuando el microservicio se arranca, automáticamente este método se arranca
		//Queremos comunicarnos con el microservicio del 44 y que lo que le vaya llegando 
		//lo vaya imprimiento en la consola
		
				
		//dirección del recurso remoto
		String url="http://localhost:8080/buscar";
		
		//como restclient
		WebClient client=WebClient.create();
		
		//hacemos la llamada para suscribirnos al flujo de datos
		//petición get
		client.get()
			.uri(url+"?tematica=libros")
			.retrieve()
			.bodyToFlux(Resultado.class)  //Flux<Resultado>le decimos a que lo tiene que transformar
			.doOnComplete(()->System.out.println("Ya no hay más"))//Flux<Resultado>
			.subscribe(r->System.out.println(r.getUrl()));  //nos suscribimos y le decimos lo que tiene que hacer con los objetos del flujo en el consumer

	}

}
