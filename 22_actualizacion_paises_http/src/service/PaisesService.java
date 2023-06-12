package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import model.Pais;

public class PaisesService {
	String url="jdbc:mysql://localhost:3307/mundo";
	String usuario="root";
	String password="root";
	String dir="https://restcountries.com/v2/all";
	public boolean guardarPais(Pais p) {
		 try (Connection con = DriverManager.getConnection(url, usuario, password)) {	           
	            String sql="insert into paises values(?,?,?,?,?)";
	            PreparedStatement ps=con.prepareStatement(sql);
	            ps.setString(1, p.getCodigoPais());
	            ps.setString(2, p.getNombre());
	            ps.setString(3, p.getContinente());
	            ps.setLong(4, p.getPoblacion());
	            ps.setString(5, p.getBandera());
	            ps.execute();
	            return true;
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	}
	public void actualizarPais(Pais p) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {	           
            String sql="update paises set nombre=?,continente=?,poblacion=?,bandera=? where codigoPais=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(5, p.getCodigoPais());
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getContinente());
            ps.setLong(3, p.getPoblacion());
            ps.setString(4, p.getBandera());
            ps.execute();
	 
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	public boolean existePais(String codigo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {	           
            String sql="select nombre from paises where codigoPais=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, codigo);
            
            ResultSet rs=ps.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}
	public List<Pais> paisesFromJson(){
		Gson gson=new Gson();
		try {
			HttpRequest request=HttpRequest.newBuilder()
								.uri(URI.create(dir))
								.GET()
								.build();
			HttpClient client=HttpClient.newBuilder()
								.build();
			HttpResponse<String> respuesta=client.send(request, BodyHandlers.ofString());
			return List.of(gson.fromJson(respuesta.body(), Pais[].class));
		}catch(Exception ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	
	public void borrarTablaPaises() {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {	           
            String proc="{call borrarTodo()}";
            CallableStatement cs=con.prepareCall(proc);
            cs.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
	}
}
