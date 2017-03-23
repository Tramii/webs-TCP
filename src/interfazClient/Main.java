package interfazClient;

import mundoClient.Cliente;

public class Main {
	
	public Main(){
		System.out.println("creando cliente en main");
		Cliente client = new Cliente();
	}
	
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
