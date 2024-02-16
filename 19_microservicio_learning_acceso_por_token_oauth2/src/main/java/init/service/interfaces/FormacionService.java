package init.service.interfaces;

import java.util.List;

import init.model.Formacion;

public interface FormacionService {

	/*
-catálogo de formaciones (curso)
-Lista de formaciones por duración máxima (esta funcionalidad no existe en el 02) (hay que pedir todos y filtrar por duración)
-alta de formaciones (en la base de datos del 02_servicio_crud_cursos)
	 */
	
	List<Formacion> catalogo();
	
	List<Formacion> catalogoPorDuracionMax(int max);
	
	void alta(Formacion formacion);
	
	
	
	
}
