package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Vehiculo;
import init.service.interfaces.RastreatorService;



@Service
public class RastreatorServiceImpl implements RastreatorService {

	
	
	//RestClient para acceder al servicio web
		@Autowired
		RestClient restClient;
			
		//Declaramos una variable String con la dirección base:
		//String urlBase="http://localhost:7001/coches/";
	
		String urlBase="http://servicio-coches/";
		
		
		
	//método para pedir todos los coches al servicio http://localhost:7001/coches/catalogo

	@Override
		public List<Vehiculo> vehiculos() {
		
		return Arrays.asList(restClient.get()
				.uri(urlBase+"catalogo")
				.retrieve()
				.body(Vehiculo[].class)				
				);
		}



	//búsqueda por rango de km
	@Override
	public List<Vehiculo> vehiculosRangoKm(int kilometrosmin, int kilometrosmax) {

		return vehiculos().stream()
				.filter(v->v.getKilometros()>=kilometrosmin&&v.getKilometros()<=kilometrosmax)
				.peek(v->v.setTipo("coche"))  //coge el array, hacemos algo y devuelve el mismo array //definimos atributo tipo como "coche"
				.toList();
				
				
	}

	
	
	//Búsqueda por precio máximo //llámamos al método ya está filtrando por precio máximo en el Servicio original del 08
	@Override
	public List<Vehiculo> vehiculosPrecioMax(double precio) {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"buscarPrecioMax/"+precio)
				.retrieve()
				.body(Vehiculo[].class)				
				).stream()
				.peek(v->v.setTipo("coche"))
				.toList();
	}

}
