package init.service.implementation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import init.model.Coche;
import init.service.interfaces.CochesService;



@Service
public class CochesServiceImpl implements CochesService {

	
	static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	static List<Coche> coches = new ArrayList<>(cargarCoches());
	
	
	static List<Coche> cargarCoches() {
		try {
			return List.of( new Coche("1111AAA", "Marca1", "Modelo1,1", 1000, 10000, sdf.parse("01/01/2001")),
							  new Coche("1111BBB", "Marca1", "Modelo1,2", 1200, 12000, sdf.parse("02/01/2001")),
							  new Coche("2222AAA", "Marca2", "Modelo2,1", 2000, 20000, sdf.parse("02/02/2002")),
							  new Coche("2222BBB", "Marca2", "Modelo2,2", 2200, 22000, sdf.parse("02/02/2002")),
							  new Coche("3333AAA", "Marca3", "Modelo3,1", 3000, 30000, sdf.parse("03/03/2003")),
							  new Coche("4444AAA", "Marca4", "Modelo4,1", 4000, 40000, sdf.parse("04/04/2004")),
							  new Coche("5555AAA", "Marca5", "Modelo5,1", 5000, 50000, sdf.parse("05/05/2005")),
							  new Coche("6666AAA", "Marca6", "Modelo6,1", 6000, 60000, sdf.parse("06/06/2006")),
							  new Coche("7777AAA", "Marca7", "Modelo7,1", 7000, 70000, sdf.parse("07/07/2007")),
							  new Coche("8888AAA", "Marca8", "Modelo8,1", 8000, 80000, sdf.parse("08/08/2008")),
							  new Coche("9999AAA", "Marca9", "Modelo9,1", 9000, 90000, sdf.parse("09/09/2009")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	} 
	private Coche buscarCochePorMatricula(String matricula) {
		return coches.stream()
				.filter(c->c.getMatricula().equals(matricula))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public void alta(Coche coche) {
		if(buscarCochePorMatricula(coche.getMatricula())==null) {
			coches.add(coche);
		}

	}
	@Override
	public Coche eliminar(String matricula) {
		Coche encontrado=buscarCochePorMatricula(matricula);
		if(encontrado!=null) {
			coches.removeIf(c->c.getMatricula().equals(matricula));
			
		}
		return encontrado;
	}

	@Override
	public void actualizar(Coche coche) {
		Coche encontrado=buscarCochePorMatricula(coche.getMatricula());
		if(encontrado!=null) {
			encontrado.setKilometros(coche.getKilometros());
			encontrado.setPrecio(coche.getPrecio());
		}

	}

	@Override
	public List<Coche> cochesMarca(String marca) {
		return coches.stream()
				.filter(c->c.getMarca().equals(marca))
				.toList();
	}

	@Override
	public List<Coche> cochesPrecioMax(double precio) {
		return coches.stream()
				.filter(c->c.getPrecio()<=precio)
				.toList();
	}

	@Override
	public List<Coche> catalogo() {
		return coches;
	}

}
