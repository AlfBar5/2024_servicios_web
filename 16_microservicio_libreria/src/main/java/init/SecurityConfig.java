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
	
	@Bean
	public JdbcUserDetailsManager users() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity");
		ds.setUsername("root");
		ds.setPassword("root");
		JdbcUserDetailsManager jdbc=new JdbcUserDetailsManager(ds);
		jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
		jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
		return jdbc;
	}
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/buscarlibro/*").authenticated()
				//.requestMatchers(HttpMethod.GET, "/cursos/*/*").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				//.requestMatchers(HttpMethod.DELETE, "/eliminar").hasAnyRole("ADMINS","OPERATORS")
				//.requestMatchers(HttpMethod.PUT, "/actualizar").hasAnyRole("OPERATORS")
				.anyRequest().permitAll()
				)
		
		/*
				aut->aut.requestMatchers(HttpMethod.GET, "/buscarlibro/*").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				.anyRequest().permitAll()
				)
		*/		
		
		.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}

}
