package init.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//colocamos antes de la clase lo que queremos que haga la librería LOMBOK
//Coloca el código durante la compilación del código
//@Data agrupa al setter y al getter y tiene más cosas

@NoArgsConstructor
@AllArgsConstructor
@Data


public class Libro {
	
	//@JsonProperty(value = "codigoisbn")
	private int isbn;
	
	//@JsonProperty(value = "titulolibro")
	private String titulo;
	
	////@JsonProperty(value = "categoria")
	private String tematica;
	

}
