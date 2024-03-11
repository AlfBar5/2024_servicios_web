package init.service.implementation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import init.model.Resultado;
import init.service.interfaces.BuscadorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class BuscadorServiceImpl implements BuscadorService {
	
	//Datos
	//PONER LA LISTA A static para que no se pierdan los resultados. 
	//Para que quede en memoria mientras la aplicación esté en uso
	static List<Resultado> resultados=new ArrayList<>(List.of(
			new Resultado("http://www.fnac.es","libros","Libros y más"),
			new Resultado("http://www.mybook.com","libros","librería de temas varios"),
			new Resultado("http://www.game.es","juegos","Juegos variados"),
			new Resultado("http://www.music.es","música","Lamejor música"),
			new Resultado("http://www.tech.com","libros","Libros técnicos"),
			new Resultado("http://www.eljuego.es","juegos","Juegos on-line")
			));
	
	
	
	
	//devolver todos los resultados NO USADO
	@Override
	public Flux<Resultado> todos() {

		
		return Flux.fromIterable(resultados);  //Flux de todos los resultados
		
		/*
		return resultados.stream()
				.toList();
		*/		
				
	}
	

	
	@Override
	public Flux<Resultado> buscar(String tematica) {
		
		
		return Flux.fromIterable(resultados)  //Flux de todos los resultados
				.filter(r->r.getTematica().equals(tematica)) //Flux de resultados por temática
				.delayElements(Duration.ofSeconds(3));  //retrasa la entrega tres segundos para simular el flujo
		
		/*
		return resultados.stream()
				.filter(r->r.getTematica().equals(tematica))
				.toList();
				
		*/			
	}
	
	@Override
	public Mono<Void> agregarResultado(Resultado resultado) {
		
		return null;
		
		/*
		return Mono.
				resultados.add(resultado);
		
		*/
			//	resultados.add(resultado);
	}

	
	//eliminar resultado y devolver todos los resultados
	@Override
	public Flux<Resultado> eliminarResultado(String url) {
		
		return null;
		
		/*
		//elimina
		resultados.removeIf(r->r.getUrl().equals(url));
		//devuelve la lista de resultados
		return resultados;
		
		*/
	}

	
	@Override
	public Mono<Resultado> actualizarDescripcion(String url, String nuevaDescripcion) {
		
		
		return null;
		
		/*
		Resultado result=resultados.stream()
				.filter(r->r.getUrl().equals(url))
				.findFirst()   //devuelve el primero que encuentra envuelto en un optional
				.orElse(null); //devuelve null si no existe
		
		
		//si encuentra algún resultado válido, cambia la descripción
		if(result!=null) {
		//actualiza la descripción
		result.setDescripcion(nuevaDescripcion);
		}
		
		// y devuelve el objeto Resultado
		return result;
		
		*/
		
	}
	
	
	

}
