package mundoServer;

import java.io.*;
import java.net.Socket;


public class ServidorHilo extends Thread {
	
	private Servidor servidor;
	private Socket cliente;
	private BufferedReader inFromClient;
	private PrintWriter outToClient;
	
	private InputStream inFromFile;
	
	//TCP
	private OutputStream output;
	private DataOutputStream outputData;
	
	 
	private Files files;
	private int id;
	
	
	public ServidorHilo (Servidor server, Socket client, int idC)throws Exception
	{
		id=idC;
		servidor = server;
		cliente = client;
		
		inFromClient =new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		outToClient =new PrintWriter(client.getOutputStream(), true);
		
		output = client.getOutputStream();
		outputData = new DataOutputStream(output);
		
		files = new Files();
		
	}
	
	
	public void run()
	{
		try {
			System.out.println("\n hilo para cliente "+id );
			String recibeSaludo = inFromClient.readLine();
			System.out.println("saludo "+ recibeSaludo);
			outToClient.println("HI "+files.darListaTitulos());
			System.out.println("envia inicio de conversacion "+"HI "+files.darListaTitulos());
			String in = inFromClient.readLine();
			System.out.println("El cliente quiere "+in);
			File file = files.returnFile(in);
			
			if(file == null)
			{
				outToClient.println("ERROR file not found");
			}
			else
			{
				//buffer de 1kB
				cliente.setReceiveBufferSize(1024);
				
				//aqui estoy mandando x mensajes de 1 MB
				long cuantosBytes = file.length();
				double cuantosMB =  cuantosBytes/(1024*1024);
				outToClient.println(cuantosMB);//le indico al cliente cuantos
				//mensajes voy a mandar
				System.out.println("voy a mandar "+cuantosMB+" MB \n");
				
				
				//va a leer de a 1024 Bytes (mensaje) y mandarlos
		        byte[] bytes = new byte[ 1024*16];
				long lleva=0;
				
				inFromFile = new FileInputStream(file);
				
		        int count;
		        while ((count = inFromFile.read(bytes)) > 0) {
		            output.write(bytes, 0, count);
		            System.out.println("mandando al cliente el file");
		        }
		       
		        System.out.println("termina de mandar el archivo");
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
			System.out.println("\n termina el cliente con id "+id +"\n");
		}
	}
	public void modificarId(int nuevo)
	{
		id=nuevo;
	}

}
