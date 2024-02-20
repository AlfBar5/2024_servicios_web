package init.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	
	private int codigoProducto;
	
	private int unidades;
	
	private double total;
	
	private Date fechaPedido;
	

}
