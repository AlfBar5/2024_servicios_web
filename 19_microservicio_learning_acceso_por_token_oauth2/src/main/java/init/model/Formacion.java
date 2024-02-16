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

//Va a consumir el servicio creado en 02_servicio_crud_cursos
//URL DE LLAMADA DEL POSTMAN: http://localhost:8080/04_servicio_learning/catalogo 
//Método GET
//Como no tienen los mismos nombres los atributos hay que darles un alias @JsonAlias 
//@JsonAlias(value = "denominacion")
//@JsonAlias(value = "duracion")

//Pasar de JAVA a JSON es serializar
//Pasar de JSON A JAVA es deserializar
//@JsonProperty actua en los dos sentidos serializar y deserializar

//Al deserializar hay que ponerle las propiedades en el json denominacion, duracion, precio

////url de prueba en el postman: http://localhost:8080/04_servicio_learning/alta-  Por POST. - marcamos body-raw-json
/*
 {
        "denominacion": "curso 5 de errores java",
        "duracion": 160,
        "precio": 223.0
    }
 */

public class Formacion {

	@JsonProperty(value = "denominacion")
	private String nombre;
	
	@JsonProperty(value = "duracion")
	private int horas;
	
	private double precio;
	
}
