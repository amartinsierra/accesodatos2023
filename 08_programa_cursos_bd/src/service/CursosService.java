package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import exceptions.ErrorLecturaDatosException;
import model.Curso;

public class CursosService {
	String url="jdbc:mysql://localhost:3307/cursosbd";
	String usuario="root";
	String password="root";
	public boolean guardarCurso(int codigoCurso, String nombre, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			String sql="insert into cursos values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			//sustituimos parámetros por valores
			ps.setInt(1, codigoCurso);
			ps.setString(2, nombre);
			ps.setDouble(3, precio);
			ps.setDate(4, Date.valueOf(fechaInicio));
			ps.setDate(5, Date.valueOf(fechaFin));
			ps.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}
	public Curso buscarCurso(int codigo) {
		Curso curso = null;
        try (Connection con = DriverManager.getConnection(url, usuario, password)) {
            String sql = "select * from cursos where codigo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return curso = new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"),
                        rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return curso;
   
    }
	public void agregarCursos(List<Curso> cursos) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			con.setAutoCommit(false);//desactivamos autocommit para que todos los execute esten en la misma transacción
			String sql="insert into cursos values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			for(Curso c:cursos) {
				ps.setInt(1, c.getCodigoCurso());
				ps.setString(2, c.getNombre());
				ps.setDouble(3, c.getPrecio());
				ps.setDate(4, Date.valueOf(c.getFechaInicio()));
				ps.setDate(5, Date.valueOf(c.getFechaFin()));
				ps.execute();
			}
			con.commit();//se confirma si todo ha ido bien
            
        } catch (SQLException ex) {
            ex.printStackTrace();
           
        }
	}
	
	public void eliminarCurso(int codigo) {
		 try (Connection con = DriverManager.getConnection(url, usuario, password)) {
             String sql = "delete from cursos where codigoCurso=?";
             PreparedStatement ps=con.prepareStatement(sql);
             ps.setInt(1, codigo);
             ps.execute();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
	}
	public List<Curso> cursosPrecioMax(double max) throws ErrorLecturaDatosException{
		 List<Curso> cursos = new ArrayList<>();
	        try (Connection con = DriverManager.getConnection(url, usuario, password)) {
	            String sql = "select * from cursos where precio>=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setDouble(1, max);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()) {
	                cursos.add(new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"),
	                        rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate()));
	            }
	            return cursos;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            //return cursos;
	            throw new ErrorLecturaDatosException("Error en acceso a la fuente de datos");
	        }
	}
	public List<Curso> cursosDuracionMax(int max){
		 List<Curso> cursos = new ArrayList<>();
	        try (Connection con = DriverManager.getConnection(url, usuario, password)) {
	            String sql = "select * from cursos where duracion<=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, max);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()) {
	                cursos.add(new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"),
	                        rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate()));
	            }
	            return cursos;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return cursos;
	            
	        }
	}
	public List<Curso> cursosFinalizados(){       
        List<Curso> cursos = new ArrayList<Curso>();
        try (Connection con = DriverManager.getConnection(url, usuario, password)) {
            String sql = "select * from cursos where fechaFin<=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cursos.add(new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"),
                        rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate()));
            }
            return cursos;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return cursos;
        }
	}
	public Curso cursoLargo() {
		Curso curso=null;
        
        try(Connection con=DriverManager.getConnection(url, usuario, password)){
            String sql = "SELECT * FROM cursos ORDER BY DATEDIFF(fechaInicio, fechaFin) LIMIT 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return curso = new Curso(rs.getInt("codigoCurso"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFin").toLocalDate());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return curso;
		/* Curso curso = null;
	        long max = 0;
	        try (Connection con = DriverManager.getConnection(url, usuario, password)) {
	            String sql = "select * from cursos";
	            Statement ps = con.createStatement();
	            ResultSet rs = ps.executeQuery(sql);
	             LocalDate fechaInicio;
	             LocalDate fechaFin;
	             Period periodo;
	             long meses;
	            while (rs.next()) {
	                fechaInicio = rs.getDate("fechaInicio").toLocalDate();
	                fechaFin = rs.getDate("fechaFin").toLocalDate();
	                periodo = Period.between(fechaInicio, fechaFin);
	                meses = periodo.toTotalMonths();
	                if(meses>max) {
	                    max = meses;
	                    curso = new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"),
	                            rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate());
	                }
	            }
	            return curso;
	            
	                
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return curso;
	        }*/
	        
	}
}
