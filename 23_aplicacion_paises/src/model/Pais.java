package model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pais {
	@SerializedName("alpha3Code")
	private String codigoPais;
	@SerializedName("name")
	private String nombre;
	@SerializedName("region")
	private String continente;
	@SerializedName("population")
	private long poblacion;
	@SerializedName("flag")
	private String bandera;
	
}
