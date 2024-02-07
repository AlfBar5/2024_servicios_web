package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	
	
	/*
	 * 
-Búsqueda de todos los cursos
-Búsqueda de curso por su id
-Búsqueda de cursos entre rango de precios
-Alta de nuevo curso a partir del JSON, devolviendo lista de cursos que quedan
-Eliminar curso por denominación, devolviendo el curso eliminado
-Actualización de precios. Se recibe la denominación y porcentaje, y se suben en ese porcentaje los precios de todos los cursos de esa denominación 
	 */
	
	///métodos que se necesitan pero los heredamos del JpaRepository 
	//no hace falta explicitarlos
	
	
	//findAll()
	
	//findById(int idCurso)
	
	//save(Curso curso); //actualizar y alta
	
	//Curso findPrecio
	
	//Comprobar si existe un curso antes de darlo de alta o eliminarlo
	Curso findByDenominacion(String denominacion);
	
	//Buscar cursos entre dos precios
	List<Curso> findByPrecioBetween(double precio1, double precio2);
	
	//Borrar productos por nombre Como es de acción hay que anotarlo
	@Transactional
	@Modifying
	void deleteByDenominacion(String denominacion);
	
	
	//actualización de precio. Este no es necesario porque podríamos hacerlo en el service
	@Transactional
	@Modifying
	@Query("update Curso c set c.precio=c.precio*(100+?1)/100 where c.denominacion=?2")
	void updatePrecio(int porcentaje, String denominacion);
	
	
	
	
}
