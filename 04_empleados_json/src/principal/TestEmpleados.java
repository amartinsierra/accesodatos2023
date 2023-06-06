package principal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Empleado;

public class TestEmpleados {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		String dir="c:\\tempo\\empleados.json";
		Gson gson=new Gson();
		Empleado[] empleados=gson.fromJson(new FileReader(dir), Empleado[].class);
		for(Empleado emp:empleados) {
			System.out.println(emp.getNombre());
			//subimos el sueldo un 10%
			emp.setSalario(emp.getSalario()*1.1);
		}
		//transformar array Java a JSON
		gson.toJson(empleados, new PrintStream(dir));
	}

}
