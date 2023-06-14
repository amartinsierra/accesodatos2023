package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientePedidos {

	public static void main(String[] args) {
		try(Socket sc=new Socket("localhost",8000);
				PrintStream out=new PrintStream(sc.getOutputStream());
				//lectura del mensaje enviado desde el servidor
				BufferedReader bf=new BufferedReader(new InputStreamReader(sc.getInputStream()));){
				
				out.println("tienda1");
					
				System.out.println("El servidor te envía: "+bf.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
