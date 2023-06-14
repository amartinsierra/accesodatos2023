package cliente;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cliente.service.ClientePedidosService;

public class Lanzador {

	public static void main(String[] args) throws IOException {
		ClientePedidosService clienteService=new ClientePedidosService();
		ExecutorService executor = Executors.newCachedThreadPool();
		/*CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(()-> clienteService.mediaUnidadesTienda("tienda1"), executor);
	    CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(()-> clienteService.mediaUnidadesTienda("tienda2"), executor);
	    CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(()-> clienteService.mediaUnidadesTienda("tienda3"), executor);
	    cf1.whenCompleteAsync((r, ex) -> System.out.println("Unidades de la tienda1: " + r));
	    cf2.whenCompleteAsync((r, ex) -> System.out.println("Unidades de la tienda2: " + r));
	    cf3.whenCompleteAsync((r, ex) -> System.out.println("Unidades de la tienda3: " + r));*/
		//System.out.println(clienteService.mediaUnidadesTienda("tienda1"));
		for(int i=1;i<=3;i++) {
			String nombre="tienda"+i;
			CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()-> clienteService.mediaUnidadesTienda(nombre), executor);
		    cf.whenCompleteAsync((r, ex) -> System.out.println("Media unidades de "+nombre+" :" + r));
		}
	    executor.shutdown();
	    System.in.read();
	}

}
