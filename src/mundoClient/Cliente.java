package mundoClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
	private InputStream inFromServer;
	private BufferedReader inFromServerLine;
	private PrintWriter outToServer;
	private String archivosDisponibles;
	private FileOutputStream fos;
	private Socket socket;
	
	private boolean estadoConectado;
	
	public Cliente()
	{
		estadoConectado=false;
	}
	
	/**
	 * queda la conexion ABIERTA si todo sale bien.
	 * Si no se hace una peticion pronto, el servidor desconecta por time out
	 * 
	 * SE CUENTAN CON 2 MINUTOS ANTES DEL TIME OUT! SE DEBE LLAMAR A 
	 * PEDIR ARCHIVO ANTES DE 2 MINUTOS
	 * 
	 * return los files disponibles en el server
	 */
	public String iniciarConexion(){
        
        archivosDisponibles = "No se pudieron recuperar archivos. Intente conectar";
        String host = "127.0.0.1";

        try {
        	System.out.println("creando socket en "+host  );
			socket = new Socket(host, 8089);
			
			estadoConectado = true;
			
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
				throw new Exception(" no respondio con HI y lista de files");
			}
			else
			{
				System.out.println("le llego un mensaje del server con los archivos");
				archivosDisponibles = inicia.replace("HI ","");
				System.out.println(archivosDisponibles);
				
			//NO cierra el socket!! no lo cierra porque todavia falta pedir las cosas!!
				
				return archivosDisponibles;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
        	try {
				socket.close();
			} catch (IOException f) {
				f.printStackTrace();
			}
        	estadoConectado = false;
		} 
        return archivosDisponibles;
	}
	
	/**
	 * Cierra conexión TCP
	 * @throws Exception
	 */
	public void cerrarConexion(){
		try {
			socket.close();
			estadoConectado = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Le llega un título por parámetro, lo recupera y lo guarda en ./descargas
	 * @param titulo
	 * @throws Exception
	 */
    public String pedirArchivo(String titulo) {
    	try{
    		if(socket == null)
    		{
    			return "error de conexion";
    		}
            outToServer.println(titulo);
            System.out.println("va a pedir "+titulo);
            File file = new File("./descargas/"+titulo);
            // Get the size of the file
            
            fos = new FileOutputStream(file);
            
            
            byte [] bytes  = new byte [1024*16];
            String tamanoEnMB = inFromServerLine.readLine();
            System.out.println("\n ya va a recibir el archivo que pesa "+tamanoEnMB);
            //int bytesRead = 0;
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
    	 catch (Exception e) {
 			e.printStackTrace();
         	try {
 				socket.close();
 				estadoConectado = false;
 			} catch (IOException f) {
 				f.printStackTrace();
 			}
         	return "error de conexion";
 		} 
    	/**
    	 * siempre que termina de descargar algo, cierra conexion
    	 
        try {
        	estadoConectado = false;
			socket.close();
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        return "el archivo fue correctamente descargado a /descargas";
    }
    
    public void detenerDescarga(){
    	
    }
    
    
    public boolean darEstadoConexion(){
    	return estadoConectado;
    }
    
    

}
