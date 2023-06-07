package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contacto;

public class ContactosService {
	String url="jdbc:mysql://localhost:3307/agenda2";
	String usuario="root";
	String password="root";
	public boolean guardarContacto(Integer numero, String nombre,String email, int edad) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
            /*String sql = "INSERT INTO contactos (nombre, email, edad, telefono) ";
            sql+="VALUES ('" + nombre + "', '" + email + "', " + edad + ", " + numero + ")";
            Statement st = con.createStatement();
            st.execute(sql);*/
			String sql="insert into contactos(nombre,email,edad,telefono) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			//sustituimos parámetros por valores
			ps.setString(1, nombre);
			ps.setString(2, email);
			ps.setInt(3, edad);
			ps.setInt(4, numero);
			ps.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}
	public Contacto buscarContacto(Integer numero) {
		Contacto contacto = null;
        String sql = "select * from contactos where telefono=?";
        
        try (Connection con = DriverManager.getConnection(url,usuario,password)){
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                contacto=new Contacto(rs.getInt("telefono"), 
                		rs.getString("nombre"), 
                		rs.getString("email"), 
                		rs.getInt("edad"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacto;
	}
	public void eliminarContacto(Integer numero) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
           /* String sql = "delete from contactos ";
            sql+="where telefono=" + numero;
            Statement st = con.createStatement();
            st.execute(sql);      */
			String sql = "delete from contactos where telefono=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	public List<Contacto> contactos() {		
		List<Contacto> contactos = new ArrayList<Contacto>();
        String sql = "select * from contactos";
        
        try (Connection con = DriverManager.getConnection(url,usuario,password)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                contactos.add(new Contacto(rs.getInt("telefono"), 
                		rs.getString("nombre"), 
                		rs.getString("email"), 
                		rs.getInt("edad")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
	}
}
