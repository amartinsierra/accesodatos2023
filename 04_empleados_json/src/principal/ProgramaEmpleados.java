package principal;

import java.util.List;
import java.util.Scanner;

import model.Empleado;
import service.EmpleadosService;

public class ProgramaEmpleados {

	static EmpleadosService empleadosService=new EmpleadosService();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int opcion;
		do {
			mostrarMenu();
			opcion=sc.nextInt();//lee la opción elegida
			switch(opcion) { //evaluamos
				case 1:
					pedirDatos();
					break;
				case 2:
					empleadosDepartamento();
					break;
				case 3:
					buscarEmpleado();
					break;
				
				case 4:
					System.out.println("---Adios---");
			}
		}while(opcion!=4);
	}
	static void mostrarMenu() {
		System.out.println("1.- Nuevo empleado");
		System.out.println("2.- Empleados departamento");
		System.out.println("3.- Buscar empleado");
		System.out.println("4.- Salir");
		
		
	}
	static void pedirDatos() {
		Scanner sc=new Scanner(System.in);
		double salario;
		String nombre,email,departamento;		
		System.out.println("Introduce salario");
		salario=Double.parseDouble(sc.nextLine());
		System.out.println("Introduce nombre");
		nombre=sc.nextLine();
		System.out.println("Introduce email");
		email=sc.nextLine();
		System.out.println("Introduce departamento");
		departamento=sc.nextLine();
		
		empleadosService.nuevoEmpleado(new Empleado(nombre,email,salario,departamento));
		
	}
	static void empleadosDepartamento() {
		Scanner sc=new Scanner(System.in);
		String departamento;
		System.out.println("Introduce departamento");
		departamento=sc.nextLine();
		List<Empleado> empleados=empleadosService.empleadosPorDepartamento(departamento);
		empleados.forEach(e->System.out.println(e.getNombre()));
	}
	static void buscarEmpleado() {
		Scanner sc=new Scanner(System.in);;
		String nombre;
		System.out.println("Introduce nombre");
		nombre=sc.nextLine();
		Empleado emp=empleadosService.buscarPorNombre(nombre);
		if(emp!=null) {
			System.out.println(emp.getNombre());
		}
	}
	

}
