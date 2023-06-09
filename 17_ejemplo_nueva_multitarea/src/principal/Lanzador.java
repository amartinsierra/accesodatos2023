package principal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tareas.TareaAscendente;
import tareas.TareaDescendente;

public class Lanzador {

	public static void main(String[] args) {
		TareaAscendente ta=new TareaAscendente();
		TareaDescendente td=new TareaDescendente();
		ExecutorService executor=Executors.newCachedThreadPool();
		executor.submit(ta);
		executor.submit(td);

	}

}
