package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	//url de prueba en el postman: http://localhost:7000/catalogo/web
	//url de prueba Gateway: http://localhost:9000/stienda/catalogo/web
	@GetMapping(value="catalogo/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> catalogotematica(@PathVariable("tematica") String tematica){
			
		return new ResponseEntity<>(service.catalogo(tematica),HttpStatus.OK);
			
	}
	
	
	
	//USUARIO AUTENTIFICADO OAUTH2
	//URL: http://localhost:7000/listatematicas
	//url de prueba Gateway: http://localhost:9000/stienda/listatematicas
	@GetMapping(value="listatematicas",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> listatematicas(){
		
		return new ResponseEntity<>(service.listaTematicas(),HttpStatus.OK);
	}
	
}




/*
 * @RestController
	public class BookController {
		@Autowired
		BookService service;
		
		@GetMapping(value="tematicas",produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<String>> tematicas(){
			return new ResponseEntity<>(service.tematicas(),HttpStatus.OK);
		}
		
		@GetMapping(value="libros/{tematica}",produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Book>> librosTematica(@PathVariable("tematica") String tema){
			return new ResponseEntity<>(service.librosTematica(tema),HttpStatus.OK);
		}
}
 */








