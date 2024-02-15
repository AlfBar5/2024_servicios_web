package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import init.model.Vehiculo;
import init.service.interfaces.RastreatorService;




@RestController
public class RastreatorController {

	
	@Autowired
	RastreatorService service;
	
	
	//url de prueba en el postman: http://localhost:7002/rastreator/todos
	@GetMapping(value="todos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Vehiculo> todos(){
		return service.vehiculos();
	}
	
	
	//url de prueba en el postman: http://localhost:7002/rastreator/vehiculos/50000
	@GetMapping(value="vehiculos/{precio}", produces=MediaType.APPLICATION_JSON_VALUE)	
	public List<Vehiculo> vehiculosPrecio(@PathVariable("precio") double precio){
			return service.vehiculosPrecioMax(precio);
			
	}

	//url de prueba en el postman: http://localhost:7002/rastreator/vehiculos/3200/6000
	@GetMapping(value="vehiculos/{km1}/{km2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Vehiculo> vehiculosKilometros(@PathVariable("km1") int km1, @PathVariable("km2") int km2){
		return service.vehiculosRangoKm(km1, km2);
	}
	
	
	
	
}
