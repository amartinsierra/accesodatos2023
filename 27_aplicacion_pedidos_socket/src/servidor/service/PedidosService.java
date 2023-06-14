package servidor.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import servidor.model.Pedido;

public class PedidosService {
	String cadenaCon="jdbc:mysql://localhost:3307/pedidos";
	String user="root";
	String pwd="root";
	
	public List<Pedido> pedidosTienda(String tienda){
		List<Pedido> pedidos = new ArrayList<>();
        String sql = "select * from pedidos where tienda=?";
        
        try (Connection con = DriverManager.getConnection(cadenaCon,user,pwd)){
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tienda);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                pedidos.add(new Pedido(rs.getInt("idPedido"), 
                		rs.getString("producto"), 
                		rs.getInt("unidades"), 
                		rs.getDate("fecha").toLocalDate(),
                		rs.getString("tienda")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
	}
}
