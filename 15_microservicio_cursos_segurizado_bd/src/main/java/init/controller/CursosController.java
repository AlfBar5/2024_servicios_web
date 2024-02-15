package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.exceptions.CursoExistenteException;
import init.model.Curso;
import init.service.interfaces.CursosService;

@RestController
public class CursosController {
	
	
	/*
-Búsqueda de todos los cursos
-Búsqueda de curso por su id
-Búsqueda de cursos entre rango de precios
-Alta de nuevo curso a partir del JSON, devolviendo lista de cursos que quedan
-Eliminar curso por denominación, devolviendo el curso eliminado
-Actualización de precios. Se recibe la denominación y porcentaje, y se suben en ese porcentaje los precios de todos los cursos de esa denominación 
	 */
	
	
	
	@Autowired
	CursosService service;
	
	
	//Búsqueda de todos los cursos. Devuelve un json de una lista de cursos
	//url para llamarlo --> http://localhost:8500/cursos
	//con un ResponseEntity
	//ACCESO LIBRE
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> cursos(){
		
		return new ResponseEntity<>(service.todosCursos(),HttpStatus.OK);
		
	}
	
	
	//Búsqueda de curso por su id. Devuelve un json del Curso
	//url para llamarlo --> http://localhost:8500/curso/curso?idCurso=2
	//ACCESO USUARIOS AUTENTICADOS
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> buscar(@RequestParam("idCurso") int idCurso) {
		
		Curso curso = service.buscarPorId(idCurso);
		//controlamos si existe el curso
		if(curso!=null) {
			return new ResponseEntity<>(curso,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	//Búsqueda de cursos entre rango de precios. Devuelve una lista de cursos json
	//url para llamarlo --> http://localhost:8500/cursos/cursos/1/999
	//dirección rest con parámetros y mismo nombre de url cursos/
	//ACCESO USUARIOS AUTENTICADOS
	@GetMapping(value="cursos/{min}/{max}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> cursosRango(@PathVariable("min") double min, @PathVariable("max")  double max) {
		
		return new ResponseEntity<>(service.buscarRangoPrecios(min, max),HttpStatus.OK);
		
	}
	
	
	//Alta de nuevo curso a partir del JSON (sin idCurso), devolviendo lista de cursos que quedan
	//url para llamarlo -->
	//Con excepción personalizada para curso con nombre repetido
	//ACCESO USUARIOS ADMINISTRADORES
	@PostMapping(value="alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> alta(@RequestBody Curso curso) {
		try {
			return new ResponseEntity<>(service.agregarCurso(curso),HttpStatus.OK);
		} catch (CursoExistenteException e) {
			//curso con nombre repetido
			//ResponseEntity(T body, HttpStatusCode status)
			//devolvemos un encabezado de códigos 400 o 500: https://developer.mozilla.org/es/docs/Web/HTTP/Status
			return new ResponseEntity<>(HttpStatus.CONFLICT);
			
		}
	}
	
	
	//Eliminar curso por denominación, devolviendo el curso eliminado json
	//url para llamarlo -->http://localhost:8500/02_servicio_crud_cursos/eliminar?nombre=php
	//ACCESO USUARIOS ADMINISTRADORES Y OPERADORES
	@DeleteMapping(value="eliminar",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso eliminar(@RequestParam("nombre") String nombre) {
		return service.eliminarCurso(nombre);
	}
	
	
	//Actualización de precios. Se recibe la denominación y porcentaje
	//url para llamarlo -->http://localhost:8500/02_servicio_crud_cursos/actualizar?porcentaje=100&nombre=curso2
	//ACCESO USUARIOS OPERADORES
	@PutMapping(value="actualizar")
	public void actualizar(@RequestParam("porcentaje") int porcentaje, @RequestParam("nombre")  String nombre) {
		service.actualizarPrecios(porcentaje, nombre);
	}
}
