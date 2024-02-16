package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import init.model.Libro;
import init.service.interfaces.LibrosService;

@RestController
public class LibrosController {

	@Autowired
	LibrosService service;
	
	//segurizado, usuario autentificado
	//Devuelve lista de todos los cursos
	//url de prueba en el postman: http://localhost:9000/catalogo/1111
	@GetMapping(value="catalogo/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Libro> catalogotematica(@PathVariable("tematica") String tematica){
			
		return service.catalogo(tematica);
			
	}
	
	
	
	
	//URL: http://localhost:9000/listatematicas
	@GetMapping(value="listatematicas",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> listatematicas(){
		
		return service.listaTematicas();
	}
	
}
