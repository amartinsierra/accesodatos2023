package tareas;

import java.util.List;

import model.Pedido;
import service.PedidosBdService;
import service.PedidosFicheroService;

public class TareaTienda implements Runnable {
	
	private String ruta;
	private String tienda;
	public TareaTienda(String ruta, String tienda) {
		this.ruta=ruta;
		this.tienda=tienda;
	}

	@Override
	public void run() {
		PedidosFicheroService fservice=new PedidosFicheroService(ruta, tienda);
		PedidosBdService bDservice=new PedidosBdService();
		//recupera los pedidos de la tienda
		List<Pedido> pedidos=fservice.pedidosTienda();
		//guarda los pedidos recuperados en la base de datos
		bDservice.guardarPedidos(pedidos);
	}

}
