package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibrosService {
	
	/*
	 
	 Tira del ejercicio 16_microservicio_libreria
	 	 
	 -catálogo de libros por temática //http://localhost:8500/libros
	 -lista de temáticas  --> //URL: http://localhost:8500/tematicas
		
	 */

	
	List<Libro> catalogo(String tematica);
	
	List<String> listaTematicas();
	
	
}
