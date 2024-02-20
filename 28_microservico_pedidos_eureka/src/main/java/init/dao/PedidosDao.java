package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Pedido;


/*
 
::::::::::::::::: SERVICIO DE PEDIDOS

------------ Expone dos recursos:

---- Un recurso que dará de alta un nuevo pedido al recibir en una petición de tipo post , que incluirá en el cuerpo de la misma los datos del pedido. Desde este servicio se realizarán las correspondientes llamadas a los recursos del servicio de productos para actualizar el stock. 


---- Un recurso que al recibir una petición get devuelva todos los pedidos registrados

 */


public interface PedidosDao extends JpaRepository<Pedido, Integer> {
	
	//-catálogo de pedidos
	//findAll()
	

	//alta pedido
	//save(Pedido pedido); //actualizar y alta
	
}
