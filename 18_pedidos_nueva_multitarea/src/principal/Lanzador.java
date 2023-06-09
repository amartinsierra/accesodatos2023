package principal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tareas.TareaTienda;

public class Lanzador {

	public static void main(String[] args) {
		TareaTienda t1=new TareaTienda("c:\\tempo\\tienda1.txt","tienda1");
		TareaTienda t2=new TareaTienda("c:\\tempo\\tienda2.txt","tienda2");
		TareaTienda t3=new TareaTienda("c:\\tempo\\tienda3.txt","tienda3");
		ExecutorService executor=Executors.newCachedThreadPool();
		executor.submit(t1);
		executor.submit(t2);
		executor.submit(t3);
		System.out.println("tareas en proceso!");
		executor.shutdown();
	}

}
