package init.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.CursosDao;
import init.exceptions.CursoExistenteException;
import init.model.Curso;
import init.service.interfaces.CursosService;


//@Service para que SPRIM cree objetos de la clase ProductosServiceImpl (para que haga los new)
@Service
public class CursosServiceImpl implements CursosService {

	
	//inyectamos el dao ProductosDao
	@Autowired
	CursosDao cursosDao;
	
	/*
	-Búsqueda de todos los cursos
	-Búsqueda de curso por su id
	-Búsqueda de cursos entre rango de precios
	-Alta de nuevo curso a partir del JSON, devolviendo lista de cursos que quedan
	-Eliminar curso por denominación, devolviendo el curso eliminado
	-Actualización de precios. Se recibe la denominación y porcentaje, y se suben en ese porcentaje los precios de todos los cursos de esa denominación 
	*/
	
	//Búsqueda de todos los cursos
	@Override
	public List<Curso> todosCursos() {
		
		return cursosDao.findAll();
	}
	
	
	//Búsqueda de curso por su id
	@Override
	public Curso buscarPorId(int idCurso) {
				
		Curso curs = cursosDao.findById(idCurso).orElse(null); //devuelve null si no existe
		
		return curs;
		
	}

	
	//Búsqueda de cursos entre rango de precios
	@Override
	public List<Curso> buscarRangoPrecios(double precio1, double precio2) {
		
		return cursosDao.findByPrecioBetween(precio1, precio2);

	}

	
	//Alta de nuevo curso a partir del JSON, devolviendo lista de cursos que quedan
	@Override
	public List<Curso> agregarCurso(Curso curso) throws CursoExistenteException {

		if(cursosDao.findByDenominacion(curso.getDenominacion())!=null) {
			//si ya hay un curso con ese nombre, provocamos una excepción
			throw new CursoExistenteException();
		}
		cursosDao.save(curso);
		return todosCursos();
		
	}

	
	//Eliminar curso por denominación, devolviendo el curso eliminado. Primero buscamos si el curso existe
	@Override
	public Curso eliminarCurso(String denominacion) {
		
		Curso curs = cursosDao.findByDenominacion(denominacion);
		
		if(curs!=null) {		
			cursosDao.deleteByDenominacion(denominacion);
			return curs;
		}else {
			return null;
		}
		
		
		
	}

	//Actualización de precios. Se recibe la denominación y porcentaje. Primero buscamos si el curso existe
	//y se suben en ese porcentaje los precios de todos los cursos de esa denominación
	@Override
	public void actualizarPrecios(int porcentaje, String denominacion) {
		
		Curso curs = cursosDao.findByDenominacion(denominacion);
		if(curs!=null) {
			cursosDao.updatePrecio(porcentaje, denominacion);
		}

	}

}
