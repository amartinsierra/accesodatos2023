package service;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Empleado;

public class EmpleadosService {
	String dir="c:\\tempo\\empleados.json";
	private Stream<Empleado> streamEmpleados(){
		Gson gson=new Gson();
		try(FileReader fr=new FileReader(dir)) {
			Empleado[] emps=gson.fromJson(fr, Empleado[].class);
			return Stream.of(emps);
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return Stream.empty(); //stream vacío
		}
	}
	
	
	public void nuevoEmpleado(Empleado empleado) {
		List<Empleado> empleados=streamEmpleados().collect(Collectors.toList());
		empleados.add(empleado);
		Gson gson=new Gson();
		try(PrintStream out=new PrintStream(dir)) {		
			gson.toJson(empleados,out);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Empleado> empleadosPorDepartamento(String dep){
		return streamEmpleados()
				.filter(e->e.getDepartamento().equals(dep))
				.collect(Collectors.toList());
	}
	public Empleado buscarPorNombre(String nombre) {
		return streamEmpleados()
				.filter(e->e.getNombre().equals(nombre))
				.findFirst()
				.orElse(null);
	}
}
