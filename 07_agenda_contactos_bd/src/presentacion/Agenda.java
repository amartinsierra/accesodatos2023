package presentacion;

import java.util.List;
import java.util.Scanner;

import model.Contacto;
import service.ContactosService;

public class Agenda {
	static ContactosService contactosService=new ContactosService();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int opcion;
		do {
			mostrarMenu();
			opcion=sc.nextInt();//lee la opción elegida
			switch(opcion) { //evaluamos
				case 1:
					pedirContacto();
					break;
				case 2:
					buscarContacto();
					break;
				case 3:
					eliminarContacto();
					break;
				case 4:
					mostrarNombres();
					break;
				
				case 5:
					System.out.println("---Adios---");
			}
		}while(opcion!=5);
	}
	static void mostrarMenu() {
		System.out.println("1.- Nuevo contacto");
		System.out.println("2.- Buscar contacto");
		System.out.println("3.- Eliminar contacto");
		System.out.println("4.- Mostrar nombres");
		System.out.println("5.- Salir");
		
	}
	static void pedirContacto() {
		Scanner sc=new Scanner(System.in);
		int numero,edad;
		String nombre,email;		
		System.out.println("Introduce número de teléfono");
		numero=Integer.parseInt(sc.nextLine());
		System.out.println("Introduce nombre");
		nombre=sc.nextLine();
		System.out.println("Introduce email");
		email=sc.nextLine();
		System.out.println("Introduce edad");
		edad=Integer.parseInt(sc.nextLine());
		
		if(contactosService.guardarContacto(numero, nombre,email,edad)) {
			System.out.println("Contacto añadido!");
		}else {
			System.out.println("Teléfono repetido, el contacto no se ha añadido!");
		}
		
	}
	static void buscarContacto() {
		Scanner sc=new Scanner(System.in);
		int numero;
		Contacto contacto;
		System.out.println("Introduce número de teléfono");
		numero=sc.nextInt();
		contacto=contactosService.buscarContacto(numero);
		//comprobamos si el contacto existe
		if(contacto!=null) {
			System.out.println(contacto.getNombre());
		}else {
			System.out.println("El contacto no existe");
		}
	}
	static void eliminarContacto() {
		Scanner sc=new Scanner(System.in);
		int numero;
		System.out.println("Introduce número de teléfono");
		numero=sc.nextInt();
		contactosService.eliminarContacto(numero);
	}
	static void mostrarNombres() {
		List<Contacto> contactos=contactosService.contactos();
		for(Contacto c:contactos) {
			System.out.println(c.getNombre());
		}
	}
}
