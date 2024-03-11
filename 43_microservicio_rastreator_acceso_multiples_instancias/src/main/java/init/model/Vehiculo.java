package init.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




//colocamos antes de la clase lo que queremos que haga la librería LOMBOK
//Coloca el código durante la compilación del código
//@Data agrupa al setter y al getter y tiene más cosas

@NoArgsConstructor
@AllArgsConstructor
@Data


/*

Microservicio que implemente API REST de vehículos de coches de segunda mano

-Cada coche tiene las siguientes propiedades:

matricula (pk)
marca
modelo
kilometros
precio
fechaFabricacion

*/

public class Vehiculo {
	
	
	private String matricula; // (pk)
	private String marca;
	private String modelo;
	private int kilometros;
	private double precio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fecha;
	
	private String tipo;
	

}
