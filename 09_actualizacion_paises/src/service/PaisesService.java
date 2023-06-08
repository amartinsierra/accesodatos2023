package service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import model.Pais;

public class PaisesService {
	String url="jdbc:mysql://localhost:3307/mundo";
	String usuario="root";
	String password="root";
	String dir="c:\\tempo\\paises.json";
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
		try(FileReader fr=new FileReader(dir)) {
			Pais[] paises=gson.fromJson(fr, Pais[].class);
			return Arrays.asList(paises);
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return List.of(); //lista vacía
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
