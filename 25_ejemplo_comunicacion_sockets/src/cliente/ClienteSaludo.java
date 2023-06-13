package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteSaludo {

	public static void main(String[] args) {
		try(Socket sc=new Socket("localhost",8000);){
			//lectura del mensaje enviado desde el servidor
			BufferedReader bf=new BufferedReader(new InputStreamReader(sc.getInputStream()));
			System.out.println("El servidor te envía: "+bf.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
