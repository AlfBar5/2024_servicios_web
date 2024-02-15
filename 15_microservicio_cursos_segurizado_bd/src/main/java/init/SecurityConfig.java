package init;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// Si es Bean, spring carga el método al arrancar
	//Tiramos de bd springsecurity
	//Creamos otro datasource nuevo que tenemos que montar nosotros, ya que ahora hay
	//dos bases de datos separadas: de negocio y de seguridad
	@Bean
	public JdbcUserDetailsManager user() {
		
		DriverManagerDataSource ds= new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity");
		ds.setUsername("root");
		ds.setPassword("root");
		//conectamos a la bd con jdbc
		JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(ds);
		//especificamos la query de si existe el usuario
		jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
		//cogemos el rol del usuario
		jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
		//retornamos el jdbc
		return jdbc;
		
		
		
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
