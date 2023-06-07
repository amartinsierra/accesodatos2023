package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarContactos {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3307/agenda2";
		String usuario="root";
		String password="root";
		try(Connection con=DriverManager.getConnection(url, usuario, password);){
			String sql="select nombre from contactos";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("nombre"));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}

	}

}
