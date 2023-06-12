package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

public class PedidosBdService {
	String cadenaCon="jdbc:mysql://localhost:3307/pedidos";
	String user="root";
	String pwd="root";
	
	
	public String productoMasVendido() {
		List<Pedido> pedidos = new ArrayList<>();
        String sql = "select * from pedidos";
        
        try (Connection con = DriverManager.getConnection(cadenaCon,user,pwd)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
        return pedidos.stream()
        		.max((p1,p2)->Integer.compare(p1.getUnidades(),p2.getUnidades()))
        		.orElse(new Pedido())
        		.getProducto();

	}
	public double mediaUnidades() {		
        String sql = "select avg(unidades) from pedidos";
        double result=0.0;
        try (Connection con = DriverManager.getConnection(cadenaCon,user,pwd)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                result=rs.getDouble(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
        return result;

	}
	public List<Pedido> pedidos() {
		List<Pedido> pedidos = new ArrayList<>();
        String sql = "select * from pedidos";
        
        try (Connection con = DriverManager.getConnection(cadenaCon,user,pwd)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
