package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorSaludo {

	public static void main(String[] args) {
		int port=8000;
		ExecutorService executor =Executors.newCachedThreadPool();
		try (ServerSocket servidor=new ServerSocket(port)){
			while(true) {
				System.out.println("A la espera de llamadas...");
				//objeto socket conectado con el cliente que ha hecho la llamada
				Socket sc=servidor.accept();
				System.out.println("Llamada recibida..");
				//operación de lectura / escritura
				//lanzamos una instancia de un runnable, que se encargará de la gestión de la
				//comunicación con cada cliente
				executor.submit(new HiloCliente(sc));
				
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
