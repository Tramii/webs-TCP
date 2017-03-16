package mundoServer;

import java.io.*;
import java.net.Socket;


public class ServidorHilo extends Thread {
	
	private Servidor servidor;
	private Socket cliente;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private Files files;
	private int id;
	
	
	public ServidorHilo (Servidor server, Socket client, int idC)throws Exception
	{
		id=idC;
		servidor = server;
		cliente = client;
		
		inFromClient =new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		outToClient = new DataOutputStream(client.getOutputStream());
		
		files = new Files();
		
	}
	
	
	public void run()
	{
		try {
			outToClient.writeBytes("HI"+files.darListaTitulos());
			
			String in = inFromClient.readLine();
			
			File file = files.returnFile(in);
			
			if(file == null)
			{
				outToClient.writeBytes("ERROR");
			}
			else
			{
				//buffer de 1kB
				cliente.setReceiveBufferSize(1024);
				//aqui estoy mandando x mensajes de 1 MB
				long cuantosBytes = file.length();
				int cuantosMB = (int) cuantosBytes/(1024*1024);
				outToClient.writeInt(cuantosMB);//le indico al cliente cuantos
				//mensajes voy a mandar
				System.out.println("voy a mandar "+cuantosMB+" MB");
				
				
				//va a leer de a 1024 Bytes (mensaje) y mandarlos
		        byte[] bytes = new byte[ 1024];
				InputStream inFromFile = new FileInputStream(file);
				int count;
		        while ((count = inFromFile.read(bytes)) > 0) {
		            outToClient.write(bytes, 0, count);
		        }
		        
		        outToClient.flush();
		        
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
		        outToClient.close();
		        inFromClient.close();
				cliente.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			servidor.saleUnCliente(id);	
			
		}
	}
	public void modificarId(int nuevo)
	{
		id=nuevo;
	}

}
