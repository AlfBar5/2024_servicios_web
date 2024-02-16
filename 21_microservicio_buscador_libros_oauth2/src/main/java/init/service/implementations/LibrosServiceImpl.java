package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;


import init.model.Libro;
import init.model.TokenResponse;
import init.service.interfaces.LibrosService;
import jakarta.annotation.PostConstruct;

@Service
public class LibrosServiceImpl implements LibrosService {

	
	/*
	Va a consumir el servicio creado en 02_servicio_crud_cursos
	-catálogo de formaciones (curso)
	-Lista de formaciones por duración máxima (esta funcionalidad no existe en el 02) (hay que pedir todos y filtrar por duración)
	-alta de formaciones (en la base de datos del 02_servicio_crud_cursos)
		 */	
		
		//RestClient para acceder al servicio web
		@Autowired
		RestClient restClient;
		
		//cuidado que hay una variable de entorno llamada username (que coje el nombre de usuario de windows)
		//por eso le llammamos app.username
		
		@Value("${app.urlAuth}")
		String urlAuth;
		@Value("${app.username}")
		String username; 
		@Value("${app.password}")
		String password;
		@Value("${app.client_id}")
		String clientId;
		@Value("${app.grant_type}")
		String grantType;
		
			
		//Declaramos una variable String con la dirección base (del ejercicio 18):
		//tienen que estar online el 18 (puerto 8500), el 19 (puerto 9000) y el servidor keycloack (http://localhost:8080/)
		String urlBase="http://localhost:8500/";
		
		
		//para no estar todo el rato consumiendo el token, guardamos el token en una variable, que es la que usamos en el método alta.
		String token;
		
		//Este método será llamado antes de pasar por todos los métodos (cuando la instancia esté disponible)
		//Si caduca dará una excepción
		//Así, si damos 10 altas, no llamamos 10 veces al servidor KEYCLOAK
		@PostConstruct
		public void init() {
			token=getToken();
		}
		
		
		
		
	//recuperar todo el catálogo de Libros 
	@Override
	public List<Libro> catalogo(String tematica) {


		
		
		//convertimos el array que recibimos an List, con Arrays.asList
		//URL: http://localhost:9000/catalogo
		return Arrays.asList(restClient.get()
				.uri(urlBase+"libros")   //llamamos al método del servicio web	
				.retrieve() 			// recogemos la respuesta, es una lista de libros
				.body(Libro[].class) //Libro[].class es un array json de objetos libro
				)
				.stream()
				.filter(l->l.getTematica().equals(tematica))
				.toList();
				
	
		
		
	}

	//USUARIO AUTENTIFICADO OAUTH2
	//convertimos el array que recibimos an List, con Arrays.asList
	//URL: http://localhost:9000/listatematicas
	@Override
	public List<String> listaTematicas() {
		//convertimos el array que recibimos an List, con Arrays.asList
				//URL: http://localhost:8080/catalogo
				return Arrays.asList(restClient.get()
						.uri(urlBase+"tematicas")   //llamamos al método del servicio web
							.header("Authorization", "Bearer "+token)  //seguridad oauth2
							.retrieve() 			// recogemos la respuesta, es una lista de tematicas
							.body(String[].class) //String[].class es un array json de string tematicas
						);
	}
	
	
	
	
	
	
	
	//método que se comunica con KEYCLOAK
	//el token es válido durante 5 minutos por defecto. Se puede subir el tiempo en el servidor KEYCLOAK
	//para no estar todo el rato consumiendo el token, guardamos el token en una variable, que es la que usamos en el método alta.
	private String getToken() {
		
		//para ver lo que llega (se ve en la consola de eclipse
		//System.out.println(username+" - "+password);
		
		//para el body --> map con las parejas clave : valor
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grantType);
		
		
		//petición post
		return restClient.post()
		.uri(urlAuth)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenResponse.class) //este es el token en bruto, tenemos que convertir este json en una clase java, un bean llamado TokenResponse.
		.getAccess_token();
	}
	

}
