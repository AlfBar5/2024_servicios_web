package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.model.Formacion;
import init.model.TokenResponse;
import init.service.interfaces.FormacionService;
import jakarta.annotation.PostConstruct;

@Service
public class FormacionServiceImpl implements FormacionService {

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
	
	
	//recuperar todo el catálogo de formaciones (curso)
	//URL: localhost:9000/catalogo
	@Override
	public List<Formacion> catalogo() {

		//convertimos el array que recibimos an List, con Arrays.asList
		return Arrays.asList(restClient.get()
				.uri(urlBase+"cursos")   //llamamos al método del servicio web
				.retrieve() 			// recogemos la respuesta, es una lista de cursos
				.body(Formacion[].class) //Formacion[].class es un array json
				);
		
		
	}
	
	//no hay ningún método en el servicio 02_servicio_crud_cursos que nos dé lo que pide este método
	//SOLUCIÓN, pedir todo y hacer un filtro
	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {

		return catalogo().stream()	//cojo el catálogo entero
				.filter(f->f.getHoras()<=max) //hago el filtro por duración máxima
				.toList();  //lo convierto a Lista
		
		
	}

	//Este método solo lo pueden consumir los usuarios ADMIN según la segurización 
	//del SERVICIO que estamos consumiendo del 18
	//localhost:9000/alta
	@Override
	public void alta(Formacion formacion) {
		//controla que haya token
		try {
			
			restClient.post()	//restclient metodo post
			.uri(urlBase+"alta") //configuro la url donde lo envío, el servicio del 02 al método "alta"
			.contentType(MediaType.APPLICATION_JSON)	//enviamos en formato json el objeto Formacion
			.body(formacion)	//Esto es lo que mando en el body
			.header("Authorization", "Bearer "+token) //SEGURIZACIÓN AUTH2
			.retrieve()		//lanzo la petición
			.toBodilessEntity(); //ResponseEntity<Void>
			
		}catch(Exception ex) {
			
			//IMPORTANTE, este try entrará en bucle si se produce otro tipo de excepción
			//HAY QUE REFINARLO con la excepción concreta que se produce en la perdida de token
			//en caso que el token caduque, se vuelve a coger el token y volver a llamar al método
			token=getToken();
			alta(formacion);
			
			//tratamiento del error por alta con nombre de curso repetido. En la consola de error del eclipse se muestra.
			//System.out.println("-------------- ERROR NOMBRE REPETIDO -------- volcado del error:");
			//ex.printStackTrace();
			
			
			
		}
		
		

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
