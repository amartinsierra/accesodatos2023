package tareas;

import java.util.stream.IntStream;

public class TareaAscendente implements Runnable {

	@Override
	public void run() {
		/*for(int i=1;i<=100;i++) {
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		IntStream.rangeClosed(1, 100)
		.forEach(n->{
			System.out.println(n);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
