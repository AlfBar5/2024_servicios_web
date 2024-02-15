package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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


///métodos que se necesitan pero los heredamos del JpaRepository 
	//no hace falta explicitarlos
	
	
	//findAll()
	
	//findById(int idCurso)
	
	//save(Curso curso); //actualizar y alta
	
	//Curso findPrecio




public interface LibrosDao extends JpaRepository<Libro, Integer> {
	
	
	//-catálogo de libros
	//findAll()
	
	
	//-lista de temáticas
	//ya tenemos la lista de todos los libros y se puede sacar de ahí
	//Pero se podría hacer una query a Libro (ENTIDAD)
	
	@Query("select distinct l.tematica from Libro l")
	public List<String> findTematicas();
	
		
	//-buscador de libro por ISBN
	//findById(int idCurso)
	
	
	//-alta de nuevos libros
	//save(Libro libro); //actualizar y alta
	
	

}
