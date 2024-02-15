package init.service.interfaces;

import java.util.List;

import init.model.Coche;

/*
 
Microservicio que implemente API REST de vehículos de coches de segunda mano

-Funcionalidades:

catálogo de todos los coches
consulta de coches por marca
consulta de coches por precio máximo
alta de coches (no se puede dar de alta un coche con matrícula repetida) 
eliminar coches por matrícula (devuelve coche eliminado)
actualización de coches (se recibe un coche y se actualizan sus datos a partir de la matricula)


 */

public interface CochesService {
	
	//catálogo de todos los coches
	List<Coche> catalogo();
	
		
	//consulta de coches por marca
	List<Coche> cochesMarca(String marca);
	
	//consulta de coches por precio máximo
	List<Coche> cochesPrecioMax(double precio);
	
	//alta de coches (no se puede dar de alta un coche con matrícula repetida) 
	void alta(Coche coche);
	
	//eliminar coches por matrícula (devuelve coche eliminado)
	Coche eliminar(String matricula);
	
	//actualización de coches (se recibe un coche y se actualizan sus datos a partir de la matricula)
	void actualizar(Coche coche);
	
	

}


