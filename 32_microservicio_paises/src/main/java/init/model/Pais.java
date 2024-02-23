package init.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//colocamos antes de la clase lo que queremos que haga la librería LOMBOK
//Coloca el código durante la compilación del código
//@Data agrupa al setter y al getter y tiene más cosas

@NoArgsConstructor
@AllArgsConstructor
@Data


//Como no tienen los mismos nombres los atributos hay que darles un alias @JsonAlias PARA DESERIALIZACIÓN
//@JsonAlias(value = "denominacion")
//@JsonAlias(value = "duracion")

//Pasar de JAVA a JSON es serializar
//Pasar de JSON A JAVA es deserializar
//@JsonAlias (Para pasar de JSON a JAVA (solo necesitamos deserializar en este ejercicio)
//@JsonProperty actua en los dos sentidos serializar y deserializar

public class Pais {

	
	@JsonAlias(value = "name")
	private String nombre;
	
	@JsonAlias(value = "region")
	private String continente;
	
	private String capital;
	
	@JsonAlias(value = "population")
	private long poblacion;
	
	@JsonAlias(value = "flag")
	private String bandera;
	
	
}
