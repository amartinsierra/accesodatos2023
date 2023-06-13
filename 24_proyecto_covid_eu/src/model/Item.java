package model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {
	@SerializedName("country")
	private String pais;
	@SerializedName("year_week")
	private String fechaMuestra;
	@SerializedName("rate_14_day")
	private double incidencia;
	@SerializedName("cumulative_count")
	private int casosAcumulados;
	@SerializedName("indicator")
	private String indicador;
}
