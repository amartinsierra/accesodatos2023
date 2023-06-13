package servidor;

import java.io.IOException;
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
		try(PrintStream out=new PrintStream(sc.getOutputStream())){
			out.println("hola");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
