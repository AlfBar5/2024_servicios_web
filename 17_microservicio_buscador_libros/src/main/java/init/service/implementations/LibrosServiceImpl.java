package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import init.model.Libro;
import init.service.interfaces.LibrosService;

@Service
public class LibrosServiceImpl implements LibrosService {

	
	/*
	 * 
	Va a consumir el servicio creado en 16_microservicio_libreria
	
	 -catálogo de libros por temática // http://localhost:8500/libros
	 -lista de temáticas  --> //URL: http://localhost:8500/tematicas
	 
	*/	
		
	//RestClient para acceder al servicio web
	@Autowired
	RestClient restClient;
	
	
	//Seguridad
	@Value("${app.user}")
	String usuario;
	@Value("${app.password}")
	String pass;
		
	//Declaramos una variable String con la dirección base:
	String urlBase="http://localhost:8500/";
		
		
	//recuperar todo el catálogo de Libros 
	@Override
	public List<Libro> catalogo(String tematica) {


		//convertimos el array que recibimos an List, con Arrays.asList
		//URL: http://localhost:8080/catalogo
		return Arrays.asList(restClient.get()
				.uri(urlBase+"libros")   //llamamos al método del servicio web
				.header("Authorization", "Basic "+getBase64(usuario,pass))  //Seguridad
				.retrieve() 			// recogemos la respuesta, es una lista de libros
				.body(Libro[].class) //Libro[].class es un array json de objetos libro
				)
				.stream()
				.filter(l->l.getTematica().equals(tematica))
				.toList();
				
		
		
		
		
	}

	//convertimos el array que recibimos an List, con Arrays.asList
	//URL: http://localhost:8080/listatematicas
	@Override
	public List<String> listaTematicas() {
		//convertimos el array que recibimos an List, con Arrays.asList
				//URL: http://localhost:8080/catalogo
				return Arrays.asList(restClient.get()
						.uri(urlBase+"tematicas")   //llamamos al método del servicio web
							.retrieve() 			// recogemos la respuesta, es una lista de tematicas
							.body(String[].class) //String[].class es un array json de string tematicas
						);
	}
	
	
	//para encriptar datos de autentificación
	private String getBase64(String us, String pwd) {
		String cad=us+":"+pwd;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	}
	
	

}
