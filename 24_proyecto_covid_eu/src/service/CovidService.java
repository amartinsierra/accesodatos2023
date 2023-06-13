package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import locator.ItemsLocator;
import model.Item;
import utilities.Utilidades;

public class CovidService {
	
	public long casosTotales() {
		LocalDate f=fechaMasReciente();
		/*return itemsFromJson() //Stream<Item>
		.filter(it->it.getIndicador().equals("cases")&&
				Utilidades.convertirTextoFecha(it.getFechaMuestra()).equals(fechaMasReciente())) //Stream<Item>
		.collect(Collectors.summingLong(it->it.getCasosAcumulados()));*/
		 return ItemsLocator.itemsFromJson() 
		            .filter(it -> it.getIndicador()
		                    .equals("cases")&& Utilidades.convertirTextoFecha(it.getFechaMuestra()).equals(f))
		            .collect(Collectors.summingLong(it->it.getCasosAcumulados()));
		
	}
	
	public LocalDate fechaMasReciente() {
		return Utilidades.convertirTextoFecha(ItemsLocator.itemsFromJson() //Stream<Item>
				.max(Comparator.comparing(it->Utilidades.convertirTextoFecha(it.getFechaMuestra()))) //Optional<Item>
				.orElse(new Item()) //Item
				.getFechaMuestra() //String
				);//LocalDate
	}
	
	
	//métodos para la incidencia media de cada pais
	public double incidenciaMedia(String pais) {
		return ItemsLocator.itemsFromJson()
				.filter(it->it.getPais().equals(pais)&&it.getIndicador().equals("cases")) //Stream<Pais>
				.collect(Collectors.averagingDouble(it->it.getIncidencia()));
				
				
	}
	
	public List<String> paises(){
		return ItemsLocator.itemsFromJson()
                .map(c->c.getPais())
                .collect(Collectors.toList());
	}
	
	//otra alternativa para la incidencia media de paises
	public Map<String,Double> inidenciasMediaPaises(){
		return ItemsLocator.itemsFromJson()
				.filter(it->it.getIndicador().equals("cases")) //Stream<Item>
				.collect(Collectors.groupingBy(it->it.getPais(), Collectors.averagingDouble(it->it.getIncidencia())));
	}
}
