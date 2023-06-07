package service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Alumno;
import model.Curso;

public class CursosService {
	
	String dir="c:\\tempo\\cursos.json";
	private Stream<Curso> streamCursos(){
		Gson gson=new Gson();
		try(FileReader fr=new FileReader(dir)) {
			Curso[] cursos=gson.fromJson(fr, Curso[].class);
			return Stream.of(cursos);
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return Stream.empty(); //stream vacío
		}
	}

	//cursos precio máximo: Recibe un precio máximo
	//y devuelve la lista de cursos cuyo precio sea inferior o igual a ese valor
	//ordenados de menor a mayor precio
	public List<Curso> cursosPrecioMax(double max){
		return streamCursos()
				.filter(c->c.getPrecio()<=max) //Stream<Curso>
				//.sorted((a,b)->Double.compare(a.getPrecio(), b.getPrecio()))
				.sorted(Comparator.comparingDouble(c->c.getPrecio()))
				.collect(Collectors.toList());
	}
	
	//a partir del nombre de un curso, devuelve la lista de alumnos de dicho curso
	public List<Alumno> alumnosCurso(String curso){
		return streamCursos()
				.filter(c->c.getCurso().equals(curso)) //Stream<Curso>
				.findFirst() //Optional<Curso>
				.orElse(new Curso())//Curso
				.getAlumnos(); //List<Alumno>
	}
	
	
	//duración media de todos los cursos
	public double duracionMedia() {
		return streamCursos()
				.collect(Collectors.averagingDouble(c->c.getDuracion()));
	}
	//media de edad de todos los alumnos
	
	public double mediaEdad() {
		return streamCursos() //Stream<Curso>
				.flatMap(c->c.getAlumnos().stream()) //Stream<Alumno>
				.collect(Collectors.averagingDouble(a->a.getEdad()));
	}
	
	//lista de nombres de todos los alumnos mayores de edad
	public List<String> nombresAlumnosMayoresEdad(){
		return streamCursos() //Stream<Curso>
				.flatMap(c->c.getAlumnos().stream()) //Stream<Alumno>
				.filter(a->a.getEdad()>18) //Stream<Alumno>
				.map(a->a.getNombre()) //Stream<String>
				.collect(Collectors.toList()); //List<String>
	}
}
