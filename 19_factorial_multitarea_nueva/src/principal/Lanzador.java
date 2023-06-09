package principal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tareas.TareaFactorial;

public class Lanzador {

	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool();
		executor.submit(new TareaFactorial(4));
		executor.submit(new TareaFactorial(8));
		executor.submit(new TareaFactorial(6));
		
		executor.shutdown();
	}

}
