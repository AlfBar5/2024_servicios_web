package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Resultado;
import service.interfaces.BuscadorService;


//Anotación RestController
@RestController
public class BuscadorController {
	//como spring es responsable de inyectar los objetos de la lógica de negocio
	//también es responsable de inyectarlos donde se le pidan
	//Con @Autowired (que es igual que el antiguo @inject, le decimos que tiene que inyectar 
	//alguna instacia de la clase que implemente esta interfaz
	
	@Autowired
	BuscadorService buscadorService;
	
	//Le vamos a pasar el resultado del método, en este caso una Lista en formato json
	@GetMapping(value="buscar", produces="application/json")
	public List<Resultado> buscar(@RequestParam("tematica") String tematica) {
						
		return buscadorService.buscar(tematica);
		
	}
	
	
	
	//recogo un json, vuelco el cuerpo de la petición en el javabin Resultado con @RequestBody
	//se volcaría en el objeto resultado
	@PostMapping(value="doNuevoResultado", consumes="application/json")
	public void agregarResultado(@RequestBody Resultado resultado) {
		
		//le pasamos el nuevo resultado al método agregarResultado
		buscadorService.agregarResultado(resultado);
		
		
				
		
	}
	
	
	
	
	
	
	
	
	
	
}
