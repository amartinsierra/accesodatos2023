package presentacion;

import java.time.LocalDate;

import exceptions.ErrorLecturaDatosException;
import service.CursosService;

public class Test {

	public static void main(String[] args) {
		CursosService cursosService=new CursosService();
		cursosService.guardarCurso(1, "Java", 150, LocalDate.of(2021, 11, 3), LocalDate.of(2022, 2, 23));
		cursosService.guardarCurso(2, "Python", 80, LocalDate.of(2022, 5, 11), LocalDate.of(2023, 4, 10));
		cursosService.guardarCurso(3, "JavaScript", 100, LocalDate.of(2020, 5, 14), LocalDate.of(2020, 7, 1));
		cursosService.guardarCurso(4, "Spring", 200, LocalDate.of(2023, 4, 5), LocalDate.of(2023, 8, 1));
		cursosService.guardarCurso(5, "Docker", 90, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 9, 4));
		
		try {
			System.out.println("Cursos de menos de 100 euros: "+cursosService.cursosPrecioMax(100).size());
		} catch (ErrorLecturaDatosException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Cursos finalizados: "+cursosService.cursosFinalizados().size());
		System.out.println("Curso más largo: "+cursosService.cursoLargo().getNombre());
	}

}
