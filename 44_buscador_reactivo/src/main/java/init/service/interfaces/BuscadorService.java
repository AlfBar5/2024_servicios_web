package init.service.interfaces;

import java.util.List;

import init.model.Resultado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Se suele cambiar list por flux y cuando se devuelve un resultado se usa mono

public interface BuscadorService {
	
	//devolver todos los resultados NO USADO
	Flux<Resultado> todos();
	
	//buscar por temática
	Flux<Resultado> buscar(String tematica);
	
	//alta de nuevo resultado	
	Mono<Void> agregarResultado(Resultado resultado);
	
	//DELETE eliminar entradas, en el que partiendo de la url, se elimina el resultado que tiene la url
	//y que también devuelva los resultados que tengan en una List
	Flux<Resultado> eliminarResultado(String url);
	
	//PUT Actualizar descripción. Recibe como parámetro la url y la nueva descripción.
	//Devuelve el objeto actualizado
	Mono<Resultado> actualizarDescripcion(String url, String nuevaDescripcion);
	
	
	
}
