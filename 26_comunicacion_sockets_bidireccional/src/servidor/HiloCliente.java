package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloCliente implements Runnable{
	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc=sc;
	}

	@Override
	public void run() {
		//gestionamos la comunicación con el cliente
		try(PrintStream out=new PrintStream(sc.getOutputStream());
				BufferedReader bf=new BufferedReader(new InputStreamReader(sc.getInputStream()))){
			
			out.println("hola "+bf.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
