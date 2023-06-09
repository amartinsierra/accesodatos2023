package principal;

import service.Contador;
import tareas.TareaVisita;

public class Lanzador {

	public static void main(String[] args) throws InterruptedException {
		Contador contador=new Contador();
		for(int i=1;i<=5;i++) {
			new Thread(new TareaVisita(contador)).start();
		}
		Thread.sleep(10000);
		System.out.println("Visitar actuales: "+contador.getValor());
	}

}
