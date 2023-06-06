package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import exceptions.ErrorLecturaDatosException;
import model.Curso;
import utilities.Utilidades;

public class CursosService {
	private final String DIR="c:\\temp\\cursos.txt";
	public boolean guardarCurso(int codigoCurso, String nombre, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		 if(buscarCurso(codigoCurso)==null) {
	            boolean res=false;
	            //guardamos contacto en fichero
	            try(FileOutputStream fos=new FileOutputStream(DIR,true);
	                    PrintStream out=new PrintStream(fos);) {
	                //creamos FileOutputStream que nos permita añadir contenido
	                out.println(codigoCurso+","+nombre+","+precio+","+Utilidades.convertirFechaATexto(fechaInicio)+","+Utilidades.convertirFechaATexto(fechaFin));
	                res = true;
	                
	            }
	            catch(IOException ex) {
	                ex.printStackTrace();
	            }
	            return res;
	            
	        }else {
	            return false;
	        }
	}
	private Curso buscarCurso(int codigo) {
        
        Curso res=null;
        //Localizamos contacto en el fichero
        try(FileReader fr = new FileReader(DIR);
                BufferedReader bf = new BufferedReader(fr);) {            
                String dato = bf.readLine(); //lee una linea del fichero
                while(dato!=null) {
                    String[] partes=dato.split(",");
                    //comprobamo si el primer elemento del array, que es el número
                    //coincide con el parámetro de entrada
                    if(Integer.parseInt(partes[0])==codigo) {
                        //Creamos el objeto contacto a partir del array de String creado
                        //con el método Split
                        res=new Curso(Integer.parseInt(partes[0]),partes[1],
                                Double.parseDouble(partes[2]),Utilidades.convertirTextoAFecha(partes[3]),
                                Utilidades.convertirTextoAFecha(partes[4]));
                        break;
                    }
                    dato = bf.readLine();
                }
            }catch(IOException ex) {
                //ex.printStackTrace();
            	crearFichero();
            }        
        return res;
    }
	//lo llamamos para que se cree el fichero
	public void crearFichero() {
		//utilizamos clase File de java.io
		File file=new File(DIR);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eliminarCurso(int codigo) {
		
	}
	public List<Curso> cursosPrecioMax(double max) throws ErrorLecturaDatosException{
		 ArrayList<Curso> cursos= new ArrayList<>();	        
	        //Buscar cursos que superen el precio dado
	        try(FileReader fr = new FileReader(DIR);
	                BufferedReader bf = new BufferedReader(fr);){
	                String dato = bf.readLine(); //lee una linea del fichero
	                
	                while(dato!=null) {
	                    String[] partes=dato.split(",");
	                    Curso cr = new Curso(Integer.parseInt(partes[0]),partes[1],
	                            Double.parseDouble(partes[2]),Utilidades.convertirTextoAFecha(partes[3]),
	                            Utilidades.convertirTextoAFecha(partes[4]));
	                    if(cr.getPrecio()<max) {
	                        cursos.add(cr);
	                    }
	                    dato = bf.readLine();
	                    
	                }
	            
	        }catch(IOException ex) {
	            ex.printStackTrace();
	            throw new ErrorLecturaDatosException("Error al obtener los cursos con precio max");
	        }                
	        
	        
	        return cursos;
	}
	public List<Curso> cursosDuracionMax(int max){
		ArrayList<Curso> cursos= new ArrayList<>();    
        
        try(FileReader fr = new FileReader(DIR);
                BufferedReader bf = new BufferedReader(fr);){
                String dato = bf.readLine(); //lee una linea del fichero
                
                while(dato!=null) {
                    String[] partes=dato.split(",");
                    Curso cr = new Curso(Integer.parseInt(partes[0]),partes[1],
                            Double.parseDouble(partes[2]),Utilidades.convertirTextoAFecha(partes[3]),
                            Utilidades.convertirTextoAFecha(partes[4]));
                    //si el periodo de tiempo entre fecha de fin y fecha de inicio es
                    //inferior al máximo, guardamos curso en el arraylist
                    if(Period.between(cr.getFechaInicio(), cr.getFechaFin()).toTotalMonths()<max) {
                        cursos.add(cr);
                    }
                    dato = bf.readLine();
                }
            
        }catch(IOException ex) {
            ex.printStackTrace();
            
        }                
        
        
        return cursos;
	}
	public List<Curso> cursosFinalizados(){
		ArrayList<Curso> cursos= new ArrayList<>();    
        
        try(FileReader fr = new FileReader(DIR);
                BufferedReader bf = new BufferedReader(fr);){
                String dato = bf.readLine(); //lee una linea del fichero
                
                while(dato!=null) {
                    String[] partes=dato.split(",");
                    Curso cr = new Curso(Integer.parseInt(partes[0]),partes[1],
                            Double.parseDouble(partes[2]),Utilidades.convertirTextoAFecha(partes[3]),
                            Utilidades.convertirTextoAFecha(partes[4]));
                    //si el periodo de tiempo entre fecha de fin y fecha de inicio es
                    //inferior al máximo, guardamos curso en el arraylist
                    if(cr.getFechaFin().isBefore(LocalDate.now())) {
                        cursos.add(cr);
                    }
                    dato = bf.readLine();
                }
            
        }catch(IOException ex) {
            ex.printStackTrace();
            
        }                
        
        
        return cursos;
	}
	public Curso cursoLargo() {
		Curso caux=null;
		//en esta variable se guarda el periodo máximo
		Period paux=Period.of(0, 0, 0);
		try(FileReader fr = new FileReader(DIR);
                BufferedReader bf = new BufferedReader(fr);){
                String dato = bf.readLine(); //lee una linea del fichero
                
                while(dato!=null) {
                    String[] partes=dato.split(",");
                    Curso cr = new Curso(Integer.parseInt(partes[0]),partes[1],
                            Double.parseDouble(partes[2]),Utilidades.convertirTextoAFecha(partes[3]),
                            Utilidades.convertirTextoAFecha(partes[4]));
                    //si el periodo de tiempo entre fecha de fin y fecha de inicio es
                    //inferior al máximo, guardamos curso en el arraylist
                    Period p=Period.between(cr.getFechaInicio(), cr.getFechaFin());
                    if(p.toTotalMonths()>paux.toTotalMonths()) {
                        caux=cr;
                        paux=p;
                    }else if(p.toTotalMonths()==paux.toTotalMonths()) {
                    	if(p.getDays()>paux.getDays()) {
                    		caux=cr;
                            paux=p;
                    	}
                    }
                    dato = bf.readLine();
                }
            
        }catch(IOException ex) {
            ex.printStackTrace();
            
        }      
		return caux;
	}
}
