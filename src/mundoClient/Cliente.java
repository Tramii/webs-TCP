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
	private FileOutputStream fos;
	
	
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
        
        fos = new FileOutputStream(file);
        
        
        byte [] bytes  = new byte [1024*16];
        String tamanoEnMB = inFromServerLine.readLine();
        System.out.println("\n ya va a recibir el archivo que pesa "+tamanoEnMB);
        int bytesRead = 0;
        int current = 0;
        
        int count;
        while ((count = inFromServer.read(bytes)) > 0) {
            fos.write(bytes, 0, count);
            current+=count;
            System.out.println("escribiendo en el archivo");
        }

 	   
        System.out.println("File " + titulo
            + " downloaded (" + current + " bytes read)");
        System.out.println("\n en la rutadelrepo/descargas se encuentra el archivo descargado");
        
    }
    
    

}
