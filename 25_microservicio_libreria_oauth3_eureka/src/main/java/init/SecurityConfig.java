package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	//Inyectamos el conversor
	@Autowired
	JwtAuthConverter jwtAuthConverter;
	
	
	//Políticas de acceso a recursos (AUTORIZAR). Spring le pasa un objeto HttpSecurity (ya creado a través del starter Spring Security
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/tematicas").authenticated()
				.requestMatchers(HttpMethod.GET, "/buscarlibro/*").authenticated()
				//.requestMatchers(HttpMethod.GET, "/cursos/*/*").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				//.requestMatchers(HttpMethod.DELETE, "/eliminar").hasAnyRole("ADMINS","OPERATORS")
				//.requestMatchers(HttpMethod.PUT, "/actualizar").hasAnyRole("OPERATORS")
				.anyRequest().permitAll()
				)
		
		
		 //Le decimos el tipo de mecanismo que va a utilizar el cliente. Lo normal es que sea autentificación base
		 //Le indicamos a Spring Security que los datos del usuario al que tiene que autorizar
		 //los proporciona el Conversor mediante Auth2
		.oauth2ResourceServer(oauth2ResourceServer-> 
	 	oauth2ResourceServer.jwt(jwt->jwt 
	 				.jwtAuthenticationConverter(jwtAuthConverter))) 
	 				.sessionManagement(sessionManagement-> 
	 					sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
		return http.build();
		
	}

}
