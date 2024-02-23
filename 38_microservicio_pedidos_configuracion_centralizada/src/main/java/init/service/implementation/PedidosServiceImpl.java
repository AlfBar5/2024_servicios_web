package init.service.implementation;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.dao.PedidosDao;
import init.model.Pedido;
import init.service.interfaces.PedidosService;



/*
 
::::::::::::::::: SERVICIO DE PEDIDOS

------------ Expone dos recursos:

---- Un recurso que dará de alta un nuevo pedido al recibir en una petición de tipo post , 
que incluirá en el cuerpo de la misma los datos del pedido. 
Desde este servicio se realizarán las correspondientes llamadas 
a los recursos del servicio de productos para actualizar el stock. 

http://localhost:8000/actualizar/37/1

---- Un recurso que al recibir una petición get devuelva todos los pedidos registrados


 */






@Service
public class PedidosServiceImpl implements PedidosService {

	
	@Autowired
	PedidosDao pedidosDao;
	
		


	//RestClient para acceder al servicio web. Tenemos que usar un RestClient el de la llamada a eureka
	//aunque le ponemos un nombre
	//la librería ribbon descubre microservicios por su nombre en eureka
	@Autowired
	RestClient restClient;



	

	
	
	
	@Override
	public List<Pedido> pedidos() {


		return pedidosDao.findAll();
		
		
	}

	//petición put a microservicio del anterior proyecto 26
	@Override
	public void altaPedido(Pedido pedido) {

			
		//Declaramos una variable String con la dirección base (del ejercicio 27):
		//tienen que estar online el 27 (puerto 8000), el eureka y este (7000) 
		//PUT
		//URL: http://localhost:8000/actualizar/37/1
		//URL del microservicio registrada en servidor EUREKA:
		String urlBase="http://servicio-tiendavirtual/";
		
						
		//se recibe un json, pero solo con los campos de codigoProducto y unidades,
		//los demos campos del model los calculamos aquí

		//llamamos al método del servicio web del proyecto 27 para pedir precio producto
		pedido.setFechaPedido(new Date());
		//obtenemos precio producto llamado al recurso remoto
		double precio=Double.parseDouble(restClient.get()
				.uri(urlBase+"precio/"+pedido.getCodigoProducto())
				.retrieve()
				.body(String.class));
		pedido.setTotal(pedido.getUnidades()*precio);
		//salvamos pedido
		pedidosDao.save(pedido);
		//actualizamos stock llamado a recurso remoto
		restClient.put()
		.uri(urlBase+"actualizar/"+pedido.getCodigoProducto()+"/"+pedido.getUnidades())
		.retrieve();
		
			
	
	
	}

}
