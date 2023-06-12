package principal;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Pedido;
import service.PedidosBdService;

public class LanzadorCompletable {

	public static void main(String[] args) {
		PedidosBdService service=new PedidosBdService();
		ExecutorService executor=Executors.newCachedThreadPool();
		CompletableFuture<String> cf1=CompletableFuture.supplyAsync(()->service.productoMasVendido(), executor);
		CompletableFuture<Double> cf2=CompletableFuture.supplyAsync(()->service.mediaUnidades(), executor);
		cf1.whenCompleteAsync((r,ex)->System.out.println("El producto más vendido es: "+r));
		cf2.whenCompleteAsync((r,ex)->System.out.println("La media de unidades es: "+r));
		List<Pedido> pedidos=service.pedidos();
		pedidos.forEach(p->	
				{
					System.out.println(p.getProducto()+"-"+p.getTienda());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				);
				
			
	}

}
