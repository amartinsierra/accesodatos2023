package service;

import java.time.LocalDate;
import java.util.List;

import exceptions.ErrorLecturaDatosException;
import model.Curso;

public class CursosService {
	String url="jdbc:mysql://localhost:3307/cursosbd";
	String usuario="root";
	String password="root";
	public boolean guardarCurso(int codigoCurso, String nombre, double precio, LocalDate fechaInicio, LocalDate fechaFin) {

	}
	private Curso buscarCurso(int codigo) {
        
   
    }
	
	public void eliminarCurso(int codigo) {
		
	}
	public List<Curso> cursosPrecioMax(double max) throws ErrorLecturaDatosException{
		
	}
	public List<Curso> cursosDuracionMax(int max){
	
	}
	public List<Curso> cursosFinalizados(){

	}
	public Curso cursoLargo() {

	}
}
