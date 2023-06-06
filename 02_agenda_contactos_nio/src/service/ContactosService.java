package service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.Contacto;

public class ContactosService {
	private final Path DIR=Paths.get("c:\\temp\\contactos.txt");
	public ContactosService() {
			if(!Files.exists(DIR)) {
				try {
					Files.createFile(DIR);
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
			}
		
	}
	public boolean guardarContacto(Integer numero, String nombre,String email, int edad) {
		if(buscarContacto(numero)==null) {
			boolean res=false;
			//guardamos contacto en fichero
			try {
	            //creamos FileOutputStream que nos permita añadir el contacto            
	            Files.writeString(DIR,numero+","+nombre+","+email+","+edad+"\n",StandardOpenOption.APPEND);  
	            res=true;
	        }
	        catch(IOException ex) {
	            ex.printStackTrace();
	        }
			return res;
		}else {
			return false;
		}
	}
	public Contacto buscarContacto(Integer numero) {
		try {
            return Files.lines(DIR) //Stream<String>
                    .map(s -> {
                    		String[] partes=s.split(",");
                    		return new Contacto(Integer.parseInt(partes[0]), partes[1], partes[2], Integer.parseInt(partes[3]));
                    	})//Stream<Contacto>
                    .filter(contacto -> contacto.getTelefono().equals(numero))
                    .findFirst()
                    .orElse(null);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	public void eliminarContacto(Integer numero) {
		//recuperamos todos los contactos en memoria (ArrayList), eliminamos el contacto
		//de la colección, y volcamos la colección en el fichero en modo sobrescritura
		List<Contacto> contactos=contactos();
		contactos.removeIf(c->c.getTelefono().equals(numero));
		try {				
			Files.write(DIR, contactos
							.stream()//Stream<Contacto>
							.map(c->c.getTelefono()+","+c.getNombre()+","+c.getEmail()+","+c.getEdad()) //Stream<String>
							.collect(Collectors.toList()));          
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
	}
	public List<Contacto> contactos() {		
		try{            
                return Files.lines(DIR)//Stream<String>
                		.map(s->{
                			String[] partes=s.split(",");
                			return new Contacto(Integer.parseInt(partes[0]),partes[1],partes[2],Integer.parseInt(partes[3]));
                		})//Stream<Contacto>
                		.collect(Collectors.toList());
        }catch(IOException ex) {
                ex.printStackTrace();
                return List.of();
        }	
		
	}
}
