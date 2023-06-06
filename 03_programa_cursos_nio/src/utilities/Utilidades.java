package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilidades {
	static final String formato="dd/MM/yyyy";
	static DateTimeFormatter formatter=DateTimeFormatter.ofPattern(formato);
	public static LocalDate convertirTextoAFecha(String fecha) {		
		return LocalDate.parse(fecha,formatter);
	}
	
	public static String convertirFechaATexto(LocalDate fecha) {		
		return fecha.format(formatter);
	}
}
