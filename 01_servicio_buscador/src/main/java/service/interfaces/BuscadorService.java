package service.interfaces;

import java.util.List;

import model.Resultado;


public interface BuscadorService {
	
	//devolver todos los resultados NO USADO
	List<Resultado> todos();
	
	//buscar por temática
	List<Resultado> buscar(String tematica);
	
	//alta de nuevo resultado	
	void agregarResultado(Resultado resultado);
	
	//DELETE eliminar entradas, en el que partiendo de la url, se elimina el resultado que tiene la url
	//y que también devuelva los resultados que tengan en una List
	List<Resultado> eliminarResultado(String url);
	
	//PUT Actualizar descripción. Recibe como parámetro la url y la nueva descripción.
	//Devuelve el objeto actualizado
	Resultado actualizarDescripcion(String url, String nuevaDescripcion);
	
	
	
}
