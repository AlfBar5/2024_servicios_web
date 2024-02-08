package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.interfaces.PaisesService;


/*

--FUENTE SERVICIO:
https://restcountries.com/v2/all


El servicio de países ofrece los siguientes recursos (todo peticiones GET):
-Lista de continentes
-Lista de paises por continente (le pasamos continente como parámetro y devuelve lista de países
-Datos del país más poblado

	 */

//hay que ir al controller del servicio rest y decirle que permita todo y no bloquee las peticiones get post. 
//Esto permite llamadas desde cualquier origen
@CrossOrigin("*") //permitir llamadas desde cualquier origen
@RestController
public class PaisesController {
	
	@Autowired
	PaisesService service;
	
	//Devuelve lista de los continentes
	//url de prueba en el postman: http://localhost:8080/05_servicio_paises/continentes
	@GetMapping(value="continentes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes(){
		
		return service.listaContinentes();
		
	}
	
	
	//url de prueba en el postman: http://localhost:8080/05_servicio_paises/paises/Europe
	@GetMapping(value="paises/{continente}",produces=MediaType.APPLICATION_JSON_VALUE)	
	public List<Pais> catalogo(@PathVariable("continente") String continente){
		
		return service.listaPaisesPorContinente(continente);
		
	}

	
	//url de prueba en el postman: http://localhost:8080/05_servicio_paises/poblado
	@GetMapping(value="poblado",produces=MediaType.APPLICATION_JSON_VALUE)
	public Pais paisMasPoblado() {
		return service.paisMasPoblado();
	}


}
