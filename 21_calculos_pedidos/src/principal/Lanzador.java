package principal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.Pedido;
import service.PedidosBdService;

public class Lanzador {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		PedidosBdService service=new PedidosBdService();
		ExecutorService executor=Executors.newCachedThreadPool();
		Future<String> f1=executor.submit(()->service.productoMasVendido());
		Future<Double> f2=executor.submit(()->service.mediaUnidades());
		//que el main vaya haciendo lo suyo, y cuando estén los resultados de las otras dos
		//los muestre
		List<Pedido> pedidos=service.pedidos();
		pedidos.forEach(p->{
				if(!f1.isDone()||!f2.isDone()) {
					System.out.println(p.getProducto()+"-"+p.getTienda());
				}
			}
		);	
		System.out.println("Producto más vendido: "+f1.get());
		System.out.println("Media de unidades: "+f2.get());
		
		executor.shutdown();
	}

}
