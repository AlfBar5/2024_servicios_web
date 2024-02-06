package service.interfaces;

import java.util.List;

import model.Resultado;


public interface BuscadorService {
	List<Resultado> buscar(String tematica);
	
	void agregarResultado(Resultado resultado);
	
}
