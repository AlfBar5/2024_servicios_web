package service.implementation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Pais;
import service.interfaces.PaisesService;




@Service
public class PaisesServiceImpl implements PaisesService{

	
	/*

	--FUENTE SERVICIO:
	https://restcountries.com/v2/all


	El servicio de países ofrece los siguientes recursos (todo peticiones GET):
	-Lista de continentes
	-Lista de paises por continente (le pasamos continente como parámetro y devuelve lista de países
	-Datos del país más poblado

		 */
	
	//RestClient para acceder al servicio web
	@Autowired
	RestClient restClient;
		
	//Declaramos una variable String con la dirección base:
	String urlBase="https://restcountries.com/v2/all";
	
	
	@Override
	public List<Pais> listaPaises() {

		return Arrays.asList(restClient.get()
				.uri(urlBase)
				.retrieve()
				.body(Pais[].class)				
				);
	
	}

	
	@Override
	public List<String> listaContinentes() {
		
		return listaPaises().stream() 		 //convertimos la lista a stream
				.map(p->p.getContinente())	//mapeamos para coger todos los continentes (region) //transforma pais en stream
				.distinct()					//distinct
				.toList();					//convertimos a lista
		
	}

	
	@Override
	public List<Pais> listaPaisesPorContinente(String continente) {
		
		return listaPaises().stream() 		 //convertimos la lista a stream
				.filter(c->c.getContinente().equals(continente))
				.toList();
						

	}

	@Override
	public Pais paisMasPoblado() {

		return listaPaises().stream()   //convertimos la lista de paises
				.max(Comparator.comparingLong(p->p.getPoblacion()))  //sacamos el de mayor población
				.orElse(null);   //por si viene nulo que no casque
		
		
		
	}
	
	
	
	
	
	
	

}
