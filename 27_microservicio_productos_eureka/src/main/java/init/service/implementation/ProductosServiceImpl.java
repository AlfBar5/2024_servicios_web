package init.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.ProductosDao;
import init.model.Producto;
import init.service.interfaces.ProductosService;


/*
::::::::::::::::: SERVICIO DE PRODUCTOS

------------ Expone tres recursos:

---- Un recurso que ante una petición get, devuelve la lista de productos existentes. 

---- Un recurso que al recibir una petición put, actualiza el stock del producto indicado. 
En la URL de la petición se reciben el código de producto y unidades compradas

---- Un recurso que al recibir una petición get con el código del producto devuelva el precio unitario del mismo

*/

@Service
public class ProductosServiceImpl implements ProductosService {

	
	@Autowired
	ProductosDao productosDao;
	
	
	//todos los productos
	@Override
	public List<Producto> catalogo() {
		
		return productosDao.findAll();
		
	}

	
	@Override
	public void actualizarStock(int codigoProducto, int unidadesCompradas) {

		Producto pro = buscarProducto(codigoProducto);
		
		if(pro!=null) {
			//cogo stock actual del producto
			int stock = pro.getStock();
			//resto unidades compradas
			stock = stock-unidadesCompradas;
			//cambio stock por nuevo stock
			pro.setStock(stock);
			productosDao.save(pro);
			
		}
		
		
	}

	
	@Override
	public double verPrecio(int codigoProducto) {
				
		/* 
		//otra forma
		Optional<Producto> prodOp=productosDao.findById(codigoProducto);
		if(prodOp.isPresent()) {
				return prodOp.get().getPrecioUnitario();
		}else {
				return -1;
		}
		
		*/
		
		Producto pro = buscarProducto(codigoProducto);
		
		if(pro!=null) {
				
			double precio =pro.getPrecioUnitario();
			
			//String cadena = String.valueOf(doble);
			
			return precio;
			
		}else {
			return -1;
		}
		
		
	}

	//buscar producto por id (codigoProducto)
	@Override
	public Producto buscarProducto(int codigoProducto) {

		return productosDao.findById(codigoProducto).orElse(null);
		
		
	}

	
	
	
	

	
	
	
}
