package util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import servidor.model.Pedido;

public class Utilidades {
	
	public static String convertirListaPedidosJson(List<Pedido> pedidos) {
		Gson gson=new Gson();
		return gson.toJson(pedidos.get(0),Pedido.class);
	}
}
