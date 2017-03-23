package mundoClient;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private InputStream inFromServer;
	private BufferedReader inFromServerLine;
	private PrintWriter outToServer;
	private String archivosDisponibles;
	private BufferedOutputStream bos;
	
	
	public Cliente()
	{
        Socket socket = null;
        archivosDisponibles = "";
        String host = "127.0.0.1";

        try {
        	System.out.println("creando socket en "+host  );
			socket = new Socket(host, 8089);
			inFromServer = socket.getInputStream();
			inFromServerLine = new BufferedReader(new InputStreamReader(inFromServer));
			
			outToServer = new PrintWriter(socket.getOutputStream(), true);
			
			outToServer.println("hola!");
			
			System.out.println(" ya mando saludo");
			
			System.out.println("esperando linea del servidor");
			String inicia = inFromServerLine.readLine();
			if(!inicia.startsWith("HI"))
			{
				//bad request
				socket.close();
			}
			else
			{
				System.out.println("le llego un mensaje del server con los archivos");
				archivosDisponibles = inicia.replace("HI ","");
				System.out.println(archivosDisponibles);
				System.out.println("va a pedir "+archivosDisponibles.split(" ")[0]);
				pedirArchivo(archivosDisponibles.split(" ")[0].trim());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
        finally{
        	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        

	}
    public void pedirArchivo(String titulo) throws Exception{
        outToServer.println(titulo);
        File file = new File("./descargas/"+titulo);
        // Get the size of the file
        
        bos = new BufferedOutputStream(new FileOutputStream(file));
        
        
        byte [] mybytearray  = new byte [1024];
        String tamanoEnMB = inFromServerLine.readLine();
        System.out.println("\n ya va a recibir el archivo que pesa "+tamanoEnMB);
        int bytesRead = inFromServer.read(mybytearray,0,mybytearray.length);
        int current = bytesRead;
        
        while(bytesRead > -1)
        {
           bytesRead = inFromServer.read(mybytearray, current, (mybytearray.length-current));
           if(bytesRead >= 0){
        	   current += bytesRead;
           }
        }

        bos.write(mybytearray, 0 , current);
        bos.flush();
        System.out.println("File " + titulo
            + " downloaded (" + current + " bytes read)");
        System.out.println("\n en la rutadelrepo/descargas se encuentra el archivo descargado");
        
    }
    
    

}
