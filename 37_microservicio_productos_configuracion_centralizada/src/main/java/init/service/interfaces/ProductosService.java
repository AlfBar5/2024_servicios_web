package init.service.interfaces;

import java.util.List;

import init.model.Producto;

/*
::::::::::::::::: SERVICIO DE PRODUCTOS

------------ Expone tres recursos:

---- Un recurso que ante una petición get, devuelve la lista de productos existentes. 

---- Un recurso que al recibir una petición put, actualiza el stock del producto indicado. 
En la URL de la petición se reciben el código de producto y unidades compradas

---- Un recurso que al recibir una petición get con el código del producto devuelva el precio unitario del mismo

*/


public interface ProductosService {
	
	//catálogo de todos los productos
	List<Producto> catalogo();
	
	//actualizar stock
	void actualizarStock(int codigoProducto, int unidadesCompradas);
	
	//devolver precio
	double verPrecio(int codigoProducto);
	
	//buscar producto por codigoProducto
	Producto buscarProducto(int codigoProducto);
	
		
	

}
