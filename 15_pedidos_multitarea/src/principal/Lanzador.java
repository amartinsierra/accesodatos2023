package principal;

import tareas.TareaTienda;

public class Lanzador {

	public static void main(String[] args) {
		TareaTienda t1=new TareaTienda("c:\\tempo\\tienda1.txt","tienda1");
		TareaTienda t2=new TareaTienda("c:\\tempo\\tienda2.txt","tienda2");
		TareaTienda t3=new TareaTienda("c:\\tempo\\tienda3.txt","tienda3");
		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();		
		System.out.println("tareas en proceso!");
	}

}
