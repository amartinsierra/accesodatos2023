package principal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Pais;
import service.PaisesService;

public class Lanzador {

	public static void main(String[] args) {
		PaisesService service=new PaisesService();
		ExecutorService executor=Executors.newCachedThreadPool();
		CompletableFuture<Pais> cf=CompletableFuture.supplyAsync(()->service.paisMasPoblado(), executor);
		cf.whenCompleteAsync((p,ex)->System.out.println("El país más poblado es: "+p.getNombre()));
		service.continentes().forEach(System.out::println);
		executor.shutdown();
	}

}
