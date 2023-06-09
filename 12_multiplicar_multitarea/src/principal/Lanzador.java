package principal;

import tareas.TareaMultiplicar;

public class Lanzador {

	public static void main(String[] args) {
		TareaMultiplicar t1=new TareaMultiplicar(6);
		TareaMultiplicar t2=new TareaMultiplicar(3);
		TareaMultiplicar t3=new TareaMultiplicar(9);
		t1.start();
		t2.start();
		t3.start();
		for(int i=1;i<=10;i++) {
			System.out.println("haciendo otras cosas...");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
