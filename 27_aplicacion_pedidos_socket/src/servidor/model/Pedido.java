package servidor.model;

import java.time.LocalDate;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {
	private int idPedido;
	private String producto;
	private int unidades;
	@Expose(serialize = false)
	private LocalDate fecha;
	private String tienda;
}
