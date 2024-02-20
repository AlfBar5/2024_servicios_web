package init.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//colocamos antes de la clase lo que queremos que haga la librería LOMBOK
//Coloca el código durante la compilación del código
//@Data agrupa al setter y al getter y tiene más cosas

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="productos")
public class Producto {
	
	
	@Id
	private int codigoProducto;
	
	private String producto;
	
	private double precioUnitario;
	
	private int stock;
	
	

}
