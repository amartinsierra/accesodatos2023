package principal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tareas.TareaFactorial;

public class Lanzador {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor=Executors.newCachedThreadPool();
		Future<Long> f1=executor.submit(new TareaFactorial(4));
		Future<Long> f2=executor.submit(new TareaFactorial(8));
		Future<Long> f3=executor.submit(new TareaFactorial(6));
		//mientras haya alguna en ejecución, hacemos las tareas del main
		while(!f1.isDone()||!f2.isDone()||!f3.isDone()) {
			System.out.println("haciendo algo....");
		}
		//llega a este punto cuando las tres tareas han finalizado
		System.out.println("Factorial del 4: "+f1.get());
		System.out.println("Factorial del 8: "+f2.get());
		System.out.println("Factorial del 6: "+f3.get());
		executor.shutdown();
	}

}
