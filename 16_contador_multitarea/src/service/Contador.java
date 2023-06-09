package service;

public class Contador {
	private int valor;
	public synchronized void incrementar() {
		int actual=valor;
		actual+=1;
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		valor=actual;
	}
	public void decrementar() {
		int actual=valor;
		actual-=1;
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		valor=actual;
	}
	public int getValor() {
		return valor;
	}
	
	
}
