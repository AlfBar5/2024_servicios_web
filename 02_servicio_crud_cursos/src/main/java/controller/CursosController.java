package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Curso;
import service.interfaces.CursosService;

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
	//url para llamarlo --> http://localhost:8080/02_servicio_crud_cursos/cursos
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursos(){
		return service.todosCursos();
	}
	
	
	//Búsqueda de curso por su id. Devuelve un json del Curso
	//url para llamarlo --> http://localhost:8080/02_servicio_crud_cursos/curso?idCurso=2
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscar(@RequestParam("idCurso") int idCurso) {
		return service.buscarPorId(idCurso);
	}
	
	
	
	//Búsqueda de cursos entre rango de precios. Devuelve una lista de cursos json
	//url para llamarlo --> http://localhost:8080/02_servicio_crud_cursos/cursos/1/999
	//dirección rest con parámetros y mismo nombre de url cursos/
	@GetMapping(value="cursos/{min}/{max}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosRango(@PathVariable("min") double min, @PathVariable("max")  double max) {
		return service.buscarRangoPrecios(min, max);
	}
	
	
	//Alta de nuevo curso a partir del JSON (sin idCurso), devolviendo lista de cursos que quedan
	//url para llamarlo -->
	@PostMapping(value="alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> alta(@RequestBody Curso curso){
		return service.agregarCurso(curso);
	}
	
	//Eliminar curso por denominación, devolviendo el curso eliminado json
	//url para llamarlo -->http://localhost:8080/02_servicio_crud_cursos/eliminar?nombre=php
	@DeleteMapping(value="eliminar",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso eliminar(@RequestParam("nombre") String nombre) {
		return service.eliminarCurso(nombre);
	}
	
	
	//Actualización de precios. Se recibe la denominación y porcentaje
	//url para llamarlo -->http://localhost:8080/02_servicio_crud_cursos/actualizar?porcentaje=100&nombre=curso2
	@PutMapping(value="actualizar")
	public void actualizar(@RequestParam("porcentaje") int porcentaje, @RequestParam("nombre")  String nombre) {
		service.actualizarPrecios(porcentaje, nombre);
	}
}
