package service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Formacion;
import service.interfaces.FormacionService;

@Service
public class FormacionServiceImpl implements FormacionService {

	/*
Va a consumir el servicio creado en 02_servicio_crud_cursos
-catálogo de formaciones (curso)
-Lista de formaciones por duración máxima (esta funcionalidad no existe en el 02) (hay que pedir todos y filtrar por duración)
-alta de formaciones (en la base de datos del 02_servicio_crud_cursos)
	 */	
	
	//RestClient para acceder al servicio web
	@Autowired
	RestClient restClient;
	
	//Declaramos una variable String con la dirección base:
	String urlBase="http://localhost:8080/02_servicio_crud_cursos/";
	
	
	//recuperar todo el catálogo de formaciones (curso)
	@Override
	public List<Formacion> catalogo() {

		//convertimos el array que recibimos an List, con Arrays.asList
		return Arrays.asList(restClient.get()
				.uri(urlBase+"cursos")   //llamamos al método del servicio web
				.retrieve() 			// recogemos la respuesta, es una lista de cursos
				.body(Formacion[].class) //Formacion[].class es un array json
				);
		
		
	}
	
	//no hay ningún método en el servicio 02_servicio_crud_cursos que nos dé lo que pide este método
	//SOLUCIÓN, pedir todo y hacer un filtro
	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {

		return catalogo().stream()	//cojo el catálogo entero
				.filter(f->f.getHoras()<=max) //hago el filtro por duración máxima
				.toList();  //lo convierto a Lista
		
		
	}

	@Override
	public void alta(Formacion formacion) {
		
		restClient.post()	//restclient metodo post
		.uri(urlBase+"alta") //configuro la url donde lo envío, el servicio del 02 al método "alta"
		.contentType(MediaType.APPLICATION_JSON)	//enviamos en formato json el objeto Formacion
		.body(formacion)	//Esto es lo que mando en el body
		.retrieve();		//lanzo la petición
		

	}

}
