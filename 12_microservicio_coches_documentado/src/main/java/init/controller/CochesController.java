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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;



//hay que ir al controller del servicio rest y decirle que permita todo y no bloquee las peticiones get post. 
//Esto permite llamadas desde cualquier origen
@CrossOrigin("*") //permitir llamadas desde cualquier origen
@RestController
public class CochesController {
	
	@Autowired
	CochesService service;
	
	
	//url de prueba para sacar todos los coches
	//http://localhost:7001/coches/catalogo
	
	//URL swagger
	//http://localhost:7001/coches/swagger-ui/index.html
	
	////INFO swagger
	//@Tag. Se coloca delante de la clase controlador para agrupar las operaciones bajo una o varias etiquetas (tags). Dichas etiquetas se indican en el atributo tags.
	//@Operation. Utilizada en cada operación expuesta por el controller. El texto inicial de ayuda se indica a través de summary, mientras que en description se ofrece información más detallada. 
	//@Parameter. Se utiliza delante de cada parámetro del método controlador para informar sobre el uso del mismo
	
	@Operation(summary = "Alta de nuevos coches", description = "Recibe como parámetro un JSON con los datos del nuevo coche. Solo le da de alta si la matrícula no existe")
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@Parameter(description = "Objeto JSON con los datos del coche") @RequestBody Coche coche) {
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
	
	@Operation(summary = "Búsqueda por marca de coche", description = "Recibe como parámetro la marca del coche. Devuelve un json con los coches de esa marca")
	@GetMapping(value="buscarMarca/{marca}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> marca(@Parameter(description = "Introducir marca de los coches buscados") @PathVariable("marca")String marca){
		return service.cochesMarca(marca);
	}
	@GetMapping(value="buscarPrecioMax/{precio}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> precio(@PathVariable("precio") double precio){
		return service.cochesPrecioMax(precio);
	}
	

}
