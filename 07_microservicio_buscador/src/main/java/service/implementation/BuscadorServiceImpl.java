package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.Resultado;
import service.interfaces.BuscadorService;



@Service
public class BuscadorServiceImpl implements BuscadorService {
	
	//Datos
	//PONER LA LISTA A static para que no se pierdan los resultados. 
	//Para que quede en memoria mientras la aplicación esté en uso
	static List<Resultado> resultados=new ArrayList<>(List.of(
			new Resultado("http://www.fnac.es","libros","Libros y más"),
			new Resultado("http://www.mybook.com","libros","librería de temas varios"),
			new Resultado("http://www.game.es","juegos","Juegos variados"),
			new Resultado("http://www.music.es","música","Lamejor música"),
			new Resultado("http://www.tech.com","libros","Libros técnicos"),
			new Resultado("http://www.eljuego.es","juegos","Juegos on-line")
			));
	
	
	
	
	//devolver todos los resultados NO USADO
	@Override
	public List<Resultado> todos() {

		return resultados.stream()
				.toList();
	}
	

	
	@Override
	public List<Resultado> buscar(String tematica) {
		return resultados.stream()
				.filter(r->r.getTematica().equals(tematica))
				.toList();
	}
	
	@Override
	public void agregarResultado(Resultado resultado) {
		
				resultados.add(resultado);
	}

	
	//eliminar resultado y devolver todos los resultados
	@Override
	public List<Resultado> eliminarResultado(String url) {
		//elimina
		resultados.removeIf(r->r.getUrl().equals(url));
		//devuelve la lista de resultados
		return resultados;
		
	}

	
	@Override
	public Resultado actualizarDescripcion(String url, String nuevaDescripcion) {
		
		Resultado result=resultados.stream()
				.filter(r->r.getUrl().equals(url))
				.findFirst()   //devuelve el primero que encuentra envuelto en un optional
				.orElse(null); //devuelve null si no existe
		
		
		//si encuentra algún resultado válido, cambia la descripción
		if(result!=null) {
		//actualiza la descripción
		result.setDescripcion(nuevaDescripcion);
		}
		
		// y devuelve el objeto Resultado
		return result;
		
	}
	
	

}
