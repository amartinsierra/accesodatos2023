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
	
	public boolean guardarContacto(Integer numero, String nombre,String email, int edad) {
		
	}
	public Contacto buscarContacto(Integer numero) {
		return null;
	}
	public void eliminarContacto(Integer numero) {
		
	}
	public List<Contacto> contactos() {		
		
		return null;
	}
}
