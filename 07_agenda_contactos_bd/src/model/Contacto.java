package model;

public class Contacto {
	private Integer telefono;
	private String nombre;
	private String email;
	private int edad;
	//constructor
	public Contacto(Integer telefono, String nombre, String email, int edad) {
		this.telefono=telefono;
		this.nombre=nombre;
		this.email=email;
		this.edad=edad;
	}
	//setter y getter
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
}
