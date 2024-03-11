package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibrosService;




/*

---- Microservicio de libros (base de datos librería)

---- Expondrá los siguientes recursos:

-catálogo de libros
-lista de temáticas
-buscador de libro por ISBN
-alta de nuevos libros


---- Los recursos estarán securizados según los siguientes criterios

buscador de libro por ISBN: usuarios autenticados
alta de libros: usuarios con rol administrador
resto de recursos: acceso libre

---- Seguridad: base de datos springsecurity
  
 */


@CrossOrigin("*") //permitir llamadas desde cualquier origen
@RestController
public class LibrosController {
	
	@Autowired
	LibrosService service;
	
	
	//acceso libre
	//-catálogo de libros
	//URL: http://localhost:8500/libros
	@GetMapping(value="libros",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> cursos(){
		
		return new ResponseEntity<>(service.todosLibros(),HttpStatus.OK);
		
	}
	
	
	//acceso libre
	//-lista de temáticas
	//URL: http://localhost:8500/tematicas
	@GetMapping(value="tematicas",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> tematicas(){
		
		return new ResponseEntity<>(service.listaTematicas(),HttpStatus.OK);
		
	}
	
	
	
	//usuarios autenticados
	//-buscador de libro por ISBN
	//URL: http://localhost:8500/buscarlibro/1231
	@GetMapping(value="buscarlibro/{isbn}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> buscar(@PathVariable("isbn") int isbn) {
		
		Libro libro=service.buscarPorIsbn(isbn);
		
		if(libro!=null) {
			return new ResponseEntity<>(libro,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	//usuarios con rol administrador
	//alta de nuevos libros
	//URL post auth basic admin admin: http://localhost:8500/alta
	@PostMapping(value="alta", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alta(@RequestBody Libro libro){	
		
		if(service.altaLibro(libro)) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	

}
