package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Formacion;
import init.service.interfaces.FormacionService;

@RestController
public class FormacionController {
	
	@Autowired
	FormacionService service;
	
	//Devuelve lista de todos los cursos
	//url de prueba en el postman: http://localhost:8080/04_servicio_learning/catalogo
	@GetMapping(value="catalogo",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> catalogo(){
		
		return service.catalogo();
		
	}
	
	//Devuelve lista de cursos por duración máxima
	//url de prueba en el postman: http://localhost:8080/04_servicio_learning/catalogo/35
	@GetMapping(value="catalogo/{max}",produces=MediaType.APPLICATION_JSON_VALUE)	
	public List<Formacion> catalogo(@PathVariable("max") int max){
		return service.catalogoPorDuracionMax(max);
		
	}

	
	//alta de curso/formacion
	//Mando un json al sercicio del 02 que da de alta cursos en la bd
	//@RequestBody y consumes
	//url de prueba en el postman: http://localhost:8080/04_servicio_learning/alta-  
	//Por POST. - marcamos body-raw-json
	/*
	 {
	        "denominacion": "curso 5 de errores java",
	        "duracion": 160,
	        "precio": 223.0
	    }
	 */
	
	@PostMapping(value="alta", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Formacion formacion) {
		service.alta(formacion);
	}
	

}
