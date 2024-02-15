package init.service.interfaces;

import java.util.List;

import init.exceptions.CursoExistenteException;
import init.model.Curso;


public interface CursosService {
	
	/*
-Búsqueda de todos los cursos
-Búsqueda de curso por su id
-Búsqueda de cursos entre rango de precios
-Alta de nuevo curso a partir del JSON, devolviendo lista de cursos que quedan
-Eliminar curso por denominación, devolviendo el curso eliminado
-Actualización de precios. Se recibe la denominación y porcentaje, y se suben en ese porcentaje los precios de todos los cursos de esa denominación 
	 */
	

	//Búsqueda de todos los cursos
	List<Curso> todosCursos();
	
	//Búsqueda de curso por su id
	Curso buscarPorId(int idCurso);
	
	//Búsqueda de cursos entre rango de precios
	List<Curso> buscarRangoPrecios(double precio1, double precio2);
	
	//Alta de nuevo curso a partir del JSON, devolviendo lista de cursos que quedan	
	List<Curso> agregarCurso(Curso curso) throws CursoExistenteException;
	
	//Eliminar curso por denominación, devolviendo el curso eliminado
	Curso eliminarCurso(String denominacion);
	
	//Actualización de precios Se recibe el porcentaje y la denominacion. No devuelve nada
	void actualizarPrecios(int porcentaje, String denominacion);
	
}
