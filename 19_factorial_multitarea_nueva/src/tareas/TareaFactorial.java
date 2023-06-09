package tareas;

import java.util.concurrent.Callable;

public class TareaFactorial implements Callable<Long> {
	private int numero;
	public TareaFactorial(int numero) {
		super();
		this.numero = numero;
	}
	@Override
	public Long call() throws Exception {
		long res=1;
		for(int i=2;i<=numero;i++) {
			res*=i;
			Thread.sleep(50);
		}
		return res;
	}

}
