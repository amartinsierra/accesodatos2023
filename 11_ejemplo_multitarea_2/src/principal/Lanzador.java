package principal;

import tareas.Escritura;

public class Lanzador {

	public static void main(String[] args) {
		Escritura l1=new Escritura();
		Escritura l2=new Escritura();
		Escritura l3=new Escritura();
		l1.start();
		l2.start();
		l3.start();
	}

}
