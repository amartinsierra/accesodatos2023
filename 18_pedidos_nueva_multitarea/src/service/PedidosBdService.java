package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Pedido;

public class PedidosBdService {
	String cadenaCon="jdbc:mysql://localhost:3307/pedidos";
	String user="root";
	String pwd="root";
	
	public void guardarPedido(Pedido pedido) {
		try(Connection con=DriverManager.getConnection(cadenaCon,user,pwd);){
			String sql="insert into pedidos(producto,unidades,fecha,tienda) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, pedido.getProducto());
			ps.setInt(2, pedido.getUnidades());
			ps.setDate(3, Date.valueOf(pedido.getFecha()));
			ps.setString(4, pedido.getTienda());
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}

	}
	public void guardarPedidos(List<Pedido> pedidos) {
		try(Connection con=DriverManager.getConnection(cadenaCon,user,pwd);){
			//desactivamos autocommit para que todos los insert estén en
			//la misma tx
			con.setAutoCommit(false);
			String sql="insert into pedidos(producto,unidades,fecha,tienda) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			for(Pedido p:pedidos) {
				ps.setString(1, p.getProducto());
				ps.setInt(2, p.getUnidades());
				ps.setDate(3, Date.valueOf(p.getFecha()));
				ps.setString(4, p.getTienda());
				ps.execute();
			}
			con.commit();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}

	}
}
