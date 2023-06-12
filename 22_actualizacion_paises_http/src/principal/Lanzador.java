package principal;

import java.util.List;

import model.Pais;
import service.PaisesService;

public class Lanzador {

	public static void main(String[] args) {
		PaisesService service=new PaisesService();
		List<Pais> paises=service.paisesFromJson();
		paises.forEach(p->{
			if(service.existePais(p.getCodigoPais())) {
				service.actualizarPais(p);
			}else {
				service.guardarPais(p);
			}
		});
		//service.borrarTablaPaises();
		System.out.println("Tarea completada!");
		
	}

}
