package cliente.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

import cliente.model.Pedido;
import util.Utilidades;

public class ClientePedidosService {
	private String dir="localhost";
	private int port=8000;
	public double mediaUnidadesTienda(String tienda) {
		try (Socket sc = new Socket("localhost", 8000);
                PrintStream out = new PrintStream(sc.getOutputStream());
                BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));) {
            out.println(tienda);
            String jsonPedidos = bf.readLine();
            List<Pedido> pedidos= Utilidades.convertirJsonAPedidos(jsonPedidos);
            return pedidos.stream()
            		.collect(Collectors.averagingDouble(p->p.getUnidades()));
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
	}
}
