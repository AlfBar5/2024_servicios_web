package init.service.interfaces;

import java.util.List;

import init.model.Libro;

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




public interface LibrosService {
	
	
	//búsqueda de todos los libros
	List<Libro> todosLibros();
	
	//lista de temáticas
	List<String> listaTematicas();
	
	//busqueda de libro por su pk isbn
	Libro buscarPorIsbn(int isbn);
	
	boolean altaLibro(Libro libro);

}
