package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Producto;



/*
::::::::::::::::: SERVICIO DE PRODUCTOS

------------ Expone tres recursos:

---- Un recurso que ante una petición get, devuelve la lista de productos existentes. 

---- Un recurso que al recibir una petición put, actualiza el stock del producto indicado. 
En la URL de la petición se reciben el código de producto y unidades compradas

---- Un recurso que al recibir una petición get con el código del producto devuelva el precio unitario del mismo

*/


public interface ProductosDao extends JpaRepository<Producto, Integer> {
	
	//-catálogo de productos
	//findAll()
	
	//-buscador de producto por codigoProducto
	//findById(int codigoProducto)
	

	//actualizar stock
	//save(Producto producto); //actualizar y alta
	
	
	/*
	//con query. No es necesaria
	@Query("update Producto p set p.stock=p.stock-?2 where p.codigoProducto=?1")
	public void updateStock(int codigoProd, int unidades);
	
	*/
	
}
