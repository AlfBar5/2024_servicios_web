package init.service.interfaces;

import java.util.List;

import init.model.Vehiculo;



public interface RastreatorService {

	//busqueda todos
	List<Vehiculo> vehiculos();
	
	//búsqueda por rango de km
	List<Vehiculo> vehiculosRangoKm(int kilometrosmin, int kilometrosmax);
	
	//Búsqueda por precio máximo
	List<Vehiculo> vehiculosPrecioMax(double precio);
	
}
