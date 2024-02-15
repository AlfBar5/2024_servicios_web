package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.model.Formacion;
import init.service.interfaces.FormacionService;

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
	
	//Declaramos una variable String con la dirección base:
	String urlBase="http://localhost:8500/";
	
	
	//Estos datos se pueden poner aquí, 
	//String usuario="admin";
	//String pass="user3";
	
	//pero lo suyo es que los pongamos en el archivo application.properties
	//y se las inyectamos a las variables usuario y pass
	@Value("${app.user}")
	String usuario;
	
	@Value("${app.password}")
	String pass;
	
	//también se pueden usar variables de entorno con contenedores docker
	//y las definimos en el application.properties como app.password={$PASS}
	
	//recuperar todo el catálogo de formaciones (curso)
	//URL: http://localhost:8080/catalogo
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
	//del SERVICIO que estamos consumiendo del 13_microservicio_cursos_segurizado
	//http://localhost:8080/alta
	@Override
	public void alta(Formacion formacion) {
		try {
			
			restClient.post()	//restclient metodo post
			.uri(urlBase+"alta") //configuro la url donde lo envío, el servicio del 02 al método "alta"
			.contentType(MediaType.APPLICATION_JSON)	//enviamos en formato json el objeto Formacion
			.body(formacion)	//Esto es lo que mando en el body
			.header("Authorization", "Basic "+getBase64(usuario,pass)) //SEGURIZACIÓN SIMPLE CODIFICADA BASE 64
			.retrieve()		//lanzo la petición
			.toBodilessEntity(); //ResponseEntity<Void>
			
		}catch(HttpClientErrorException ex) {
			
			//tratamiento del error por alta con nombre de curso repetido
			System.out.println("-------------- ERROR NOMBRE REPETIDO -------- volcado del error:");
			ex.printStackTrace();
			
		}
		
		

	}

	
	//método que coje el usuario y contraseña y lo codifique a Base64
	private String getBase64(String us, String pwd) {
		//creamos cadena concatenada de usuario:password
		String cad = us+":"+pwd;
		//codificamos en base 64 con encodeToString, que le tenemos que pasar un array de bytes (lo hacemos con getBytes() )
		return Base64.getEncoder().encodeToString(cad.getBytes());
		
	}

}
