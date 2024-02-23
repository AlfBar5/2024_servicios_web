package init.service.interfaces;

import java.util.List;

import init.model.Pais;

public interface PaisesService {
	
	/*

--FUENTE SERVICIO:
https://restcountries.com/v2/all


El servicio de países ofrece los siguientes recursos (todo peticiones GET):
-Lista de continentes
-Lista de paises por continente (le pasamos continente como parámetro y devuelve lista de países
-Datos del país más poblado

	 */

	List <Pais> listaPaises();
	
	List <String> listaContinentes();
	
	List <Pais> listaPaisesPorContinente(String continente);
	
	Pais paisMasPoblado();
	
	
	
	
	
	
}
