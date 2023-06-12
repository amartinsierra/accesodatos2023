package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import model.Pais;

public class PaisesService {
	
	String dir="https://restcountries.com/v2/all";
	
	private List<Pais> paisesFromJson(){
		Gson gson=new Gson();
		try {
			HttpRequest request=HttpRequest.newBuilder()
								.uri(URI.create(dir))
								.GET()
								.build();
			HttpClient client=HttpClient.newBuilder()
								.build();
			HttpResponse<String> respuesta=client.send(request, BodyHandlers.ofString());
			return List.of(gson.fromJson(respuesta.body(), Pais[].class));
		}catch(Exception ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	
	public Pais paisMasPoblado() {
		return paisesFromJson()
		.stream()
		.max(Comparator.comparingLong(p->p.getPoblacion()))
		//.max(Comparator.comparingLong(Pais::getPoblacion))
		//.max((a,b)->Long.compare(a.getPoblacion(),b.getPoblacion()))
		.orElse(new Pais());
	}
	public List<String> continentes(){
		return paisesFromJson()
				.stream()
				.map(Pais::getContinente)
				.distinct()
				.collect(Collectors.toList());
	}
	
	
}
