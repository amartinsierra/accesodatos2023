package principal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import service.CovidService;

public class Lanzador {

	public static void main(String[] args) {
		CovidService service=new CovidService();
		ExecutorService executor=Executors.newCachedThreadPool();
		CompletableFuture<Long> cf=CompletableFuture.supplyAsync(()->service.casosTotales(),executor);
		cf.whenCompleteAsync((r,ex)->System.out.println("Casos totales: "+r));
		service.inidenciasMediaPaises()
		.forEach((k,v)->System.out.println("Pais: "+k+" Incidencia media: "+v));
		executor.shutdown();
	}

}
