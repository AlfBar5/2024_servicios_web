package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Coche;
import init.service.interfaces.CochesService;



//hay que ir al controller del servicio rest y decirle que permita todo y no bloquee las peticiones get post. 
//Esto permite llamadas desde cualquier origen
@CrossOrigin("*") //permitir llamadas desde cualquier origen
@RestController
public class CochesController {
	
	@Autowired
	CochesService service;
	
	
	//url de prueba para sacar todos los coches
	//http://localhost:7001/coches/catalogo
	
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Coche coche) {
		service.alta(coche);
	}
	@PutMapping(value="actualizar",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Coche coche) {
		service.actualizar(coche);
	}
	@DeleteMapping(value="eliminar/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Coche eliminar(@PathVariable("matricula")String matricula) {
		return service.eliminar(matricula);
	}
	@GetMapping(value="catalogo",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> catalogo(){
		return service.catalogo();
	}
	@GetMapping(value="buscarMarca/{marca}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> marca(@PathVariable("marca")String marca){
		return service.cochesMarca(marca);
	}
	@GetMapping(value="buscarPrecioMax/{precio}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> precio(@PathVariable("precio") double precio){
		return service.cochesPrecioMax(precio);
	}
	

}
