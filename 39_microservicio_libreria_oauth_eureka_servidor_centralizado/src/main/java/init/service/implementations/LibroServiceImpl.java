package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.LibrosDao;
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

---- Seguridad: oauth2
  
 */



@Service
public class LibroServiceImpl implements LibrosService {
	
	
	@Autowired
	LibrosDao librosDao;

	
		
	
	
	//libre acceso
	//-catálogo de libros
	@Override
	public List<Libro> todosLibros() {

		return librosDao.findAll();
		
	}

	
	//USUARIO AUTENTIFICADO OAUTH2
	//-lista de temáticas
	@Override
	public List<String> listaTematicas() {
		
		//una forma cogiendo una lista de todos los campos, menos optimizado si la tabla tiene muchos campos
		/*
		return librosDao.findAll().stream()
				.map(l->l.getTematica())
				.distinct()
				.toList();
		*/
		
		//usando una query con un distinct en LibrosDao, mucho más optimizado
		return librosDao.findTematicas();
		
		
	}

	//-buscador de libro por ISBN
	@Override
	public Libro buscarPorIsbn(int isbn) {
		
		return librosDao.findById(isbn).orElse(null);
		
	}

	//-alta de nuevos libros
	@Override
	public boolean altaLibro(Libro libro) {

	
		if(buscarPorIsbn(libro.getIsbn())==null) {
			librosDao.save(libro);
			return true;
		}
		return false;
				
			
		
		
	}

	
	
	
	
	
}
