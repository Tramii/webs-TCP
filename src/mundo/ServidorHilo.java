package mundo;

import java.io.*;
import java.net.Socket;

public class ServidorHilo extends Thread {
	
	private Servidor servidor;
	private Socket cliente;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	
	
	public ServidorHilo (Servidor server, Socket client)throws Exception
	{
		servidor = server;
		cliente = client;
		
		inFromClient =new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		outToClient = new DataOutputStream(client.getOutputStream());
		
	}
	
	
	public void run()
	{
		try {
			outToClient.writeBytes("HI"+);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			servidor.saleUnCliente();			
		}
	}

}
