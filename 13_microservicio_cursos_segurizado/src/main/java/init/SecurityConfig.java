package init;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	 //@Bean es para que Spring carge este método nada más arrancar
	 //Definición de roles y usuarios (AUTENTICAR Y VER ROL DE USUARIO)
	 @Bean
	 public InMemoryUserDetailsManager users () {
	 
		 //Creamos la lista de usuarios con dos perfiles/roles, el admin tiene los dos
		 //{noop} --> le decimos a spring que la contraseña es no encriptada
		 List<UserDetails> usuarios=List.of( 
						User.withUsername("user1")
						.password("{noop}user1")
						.roles("USERS")
						.build(),
						User.withUsername("user2")
						.password("{noop}user2")
						.roles("OPERATOR")
						.build(),
						User.withUsername("admin")
						.password("{noop}user3")
						.roles("USERS","ADMINS")
						.build());
					 
		return new InMemoryUserDetailsManager(usuarios);
		
	 }
	 
	 //Políticas de acceso a recursos (AUTORIZAR). Spring le pasa un objeto HttpSecurity (ya creado a través del starter Spring Security
	 @Bean
	 public SecurityFilterChain filter(HttpSecurity http) throws Exception  {
	 
		 //Desactivar un token que se utiliza en web para proteger la aplicación, pero que ahora no debe estar activado
		 //Luego, con authorizeHttpRequests, le decimos las direcciones del controller deben estar protegidas
		 //catálogo de cursos --> lo ve cualquiera, acceso libre, no hay que meterlo aquí
		 //petición get a curso --> cualquiera que esté autenticado. Protegemos dirección y método get en este caso
		 //peticion get a curso/min/max  --> cualquiera que esté autenticado
		 //petición post a alta --> Admin
		 //petición delete a eliminar --> Admin y operators
		 //peticion put a actualizar --> Operators
		 //Todo otro acceso le permitimos
		 http.csrf(c->c.disable())
		 .authorizeHttpRequests(
				 aut->aut.requestMatchers(HttpMethod.GET, "/curso").authenticated()
				 .requestMatchers(HttpMethod.GET, "/cursos/*/*").authenticated()
				 .requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				 .requestMatchers(HttpMethod.DELETE, "/eliminar").hasAnyRole("ADMINS","OPERATORS")
				 .requestMatchers(HttpMethod.PUT, "/actualizar").hasAnyRole("OPERATORS")
				 .anyRequest().permitAll()
				 )
		 
		 //Le decimos el tipo de mecanismo que va a utilizar el cliente. Lo normal es que sea autentificación base
		 .httpBasic(Customizer.withDefaults());
		 return http.build();
		 
		 
	 
	 }
	

}
