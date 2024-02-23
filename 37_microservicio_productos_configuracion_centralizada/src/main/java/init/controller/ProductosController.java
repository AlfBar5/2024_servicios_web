package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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

//para poder aceder desde el 29
@CrossOrigin("*")
@RestController
public class ProductosController {
	
	@Autowired
	ProductosService service;
	
	
	//-lista de productos
	//URL: http://localhost:8000/productos
	@GetMapping(value="productos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> productos(){
		
		return new ResponseEntity<>(service.catalogo(),HttpStatus.OK);
		
	}
	
	
	//convertir el método verPrecio a String en el controller. 
	//No se puede enviar un double, tiene que ser json o String (texto plano)
	//URL: http://localhost:8000/precio/123
	@GetMapping(value="precio/{codigo}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> buscarPrecio(@PathVariable("codigo") int codigoProducto) {
		
		return new ResponseEntity<>(Double.toString(service.verPrecio(codigoProducto)),HttpStatus.OK);
	}
	
	//PUT
	//URL: http://localhost:8000/actualizar/37/1
	@PutMapping(value="actualizar/{codigo}/{unidades}")
	public ResponseEntity<Void> actualizarStock(@PathVariable("codigo") int codigoProducto,@PathVariable("unidades") int unidades){
		
		service.actualizarStock(codigoProducto, unidades);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

}
