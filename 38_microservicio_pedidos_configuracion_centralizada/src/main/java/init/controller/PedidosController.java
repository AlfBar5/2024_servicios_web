package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Pedido;
import init.service.interfaces.PedidosService;

/*
 
::::::::::::::::: SERVICIO DE PEDIDOS

------------ Expone dos recursos:

---- Un recurso que dará de alta un nuevo pedido al recibir en una petición de tipo post , 
que incluirá en el cuerpo de la misma los datos del pedido. 
Desde este servicio se realizarán las correspondientes llamadas 
a los recursos del servicio de productos para actualizar el stock. 

http://localhost:8000/actualizar/37/1

---- Un recurso que al recibir una petición get devuelva todos los pedidos registrados


 */


//para poder aceder desde el 29
@CrossOrigin("*")
@RestController
public class PedidosController {
	
	
	@Autowired
	PedidosService service;
	
	
	//-lista de pedidos
	//URL: http://localhost:7000/pedidos
	@GetMapping(value="pedidos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> pedidos(){
			
		return new ResponseEntity<>(service.pedidos(),HttpStatus.OK);
			
	}
	
	
	//método POST mandamos json (consumes)
	//alta nuevo pedido
	//URL: http://localhost:7000/alta
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> altaPedido(@RequestBody Pedido pedido) {
	
		service.altaPedido(pedido);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
