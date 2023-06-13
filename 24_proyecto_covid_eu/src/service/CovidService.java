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

import model.Item;
import utilities.Utilidades;

public class CovidService {
	String dir="https://opendata.ecdc.europa.eu/covid19/nationalcasedeath/json/";
	
	private Stream<Item> itemsFromJson(){
		Gson gson=new Gson();
		try {
			HttpRequest request=HttpRequest.newBuilder()
								.uri(URI.create(dir))
								.GET()
								.build();
			HttpClient client=HttpClient.newBuilder()
								.build();
			HttpResponse<String> respuesta=client.send(request, BodyHandlers.ofString());
			return Arrays.stream(gson.fromJson(respuesta.body(), Item[].class));
		}catch(Exception ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
	}
	public long casosTotales() {
		LocalDate f=fechaMasReciente();
		/*return itemsFromJson() //Stream<Item>
		.filter(it->it.getIndicador().equals("cases")&&
				Utilidades.convertirTextoFecha(it.getFechaMuestra()).equals(fechaMasReciente())) //Stream<Item>
		.collect(Collectors.summingLong(it->it.getCasosAcumulados()));*/
		 return itemsFromJson() 
		            .filter(it -> it.getIndicador()
		                    .equals("cases")&& Utilidades.convertirTextoFecha(it.getFechaMuestra()).equals(f))
		            .collect(Collectors.summingLong(it->it.getCasosAcumulados()));
		
	}
	
	public LocalDate fechaMasReciente() {
		return Utilidades.convertirTextoFecha(itemsFromJson() //Stream<Item>
				.max(Comparator.comparing(it->Utilidades.convertirTextoFecha(it.getFechaMuestra()))) //Optional<Item>
				.orElse(new Item()) //Item
				.getFechaMuestra() //String
				);//LocalDate
	}
	
	
	//métodos para la incidencia media de cada pais
	public double incidenciaMedia(String pais) {
		return itemsFromJson()
				.filter(it->it.getPais().equals(pais)&&it.getIndicador().equals("cases")) //Stream<Pais>
				.collect(Collectors.averagingDouble(it->it.getIncidencia()));
				
				
	}
	
	public List<String> paises(){
		return itemsFromJson()
                .map(c->c.getPais())
                .collect(Collectors.toList());
	}
	
	//otra alternativa para la incidencia media de paises
	public Map<String,Double> inidenciasMediaPaises(){
		return itemsFromJson()
				.filter(it->it.getIndicador().equals("cases")) //Stream<Item>
				.collect(Collectors.groupingBy(it->it.getPais(), Collectors.averagingDouble(it->it.getIncidencia())));
	}
}
