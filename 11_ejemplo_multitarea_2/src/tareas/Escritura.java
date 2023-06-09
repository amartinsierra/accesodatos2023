package tareas;

public class Escritura extends Thread {

	@Override
	public void run() {
		for(int i=1;i<=100;i++) {
			System.out.println(this.hashCode());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
