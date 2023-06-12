package principal;

import service.Capsula;

public class Test {

	public static void main(String[] args) {
		Capsula<String> cp=new Capsula<>("hello");
		Capsula<Integer> cp2=new Capsula<>(200);
	}

}
