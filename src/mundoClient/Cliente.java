package mundoClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import interfazClient.InterfazCliente;

public class Cliente extends Thread{
	
	private InputStream inFromServer;
	private BufferedReader inFromServerLine;
	private PrintWriter outToServer;
	private String archivosDisponibles;
	private FileOutputStream fos;
	private Socket socket;
	
	private boolean estadoConectado;
	private String tituloAPedir;
	
	private InterfazCliente interfaz;
	public Cliente(InterfazCliente interfazC)
	{
		interfaz = interfazC;
		estadoConectado=false;
		tituloAPedir =null;
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
        	System.out.println("Creando socket en "+host  );
			socket = new Socket(host, 8089);
			
			estadoConectado = true;
			
			inFromServer = socket.getInputStream();
			inFromServerLine = new BufferedReader(new InputStreamReader(inFromServer));
			
			outToServer = new PrintWriter(socket.getOutputStream(), true);
			
			outToServer.println("hola!");
			
			System.out.println("Envía saludo");
			System.out.println("Espera respuesta del servidor");
			
			String inicia = inFromServerLine.readLine();
			if(!inicia.startsWith("HI"))
			{
				//bad request
				throw new Exception(" no respondio con HI y lista de files");
			}
			else
			{
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
	
	
	
	public void run(){
		if(tituloAPedir != null && socket != null)
		{
			pedirArchivo();
		}
	}
    public void tituloAPedir(String titulo)
    {
    	tituloAPedir = titulo;
    }
	
	/**
	 * Le llega un título por parámetro, lo recupera y lo guarda en ./descargas
	 * @param titulo
	 * @throws Exception
	 */
    public String pedirArchivo() {
    	File file = null;
    	try{
    		if(socket == null )
    		{
    			return "error de conexion";
    		}
            outToServer.println(tituloAPedir);
            System.out.println("va a pedir "+tituloAPedir);
            file = new File("./descargas/"+tituloAPedir);
            // Get the size of the file
            
            fos = new FileOutputStream(file);
            
            
            byte [] bytes  = new byte [1024*16];
            String tamanoEnMB = inFromServerLine.readLine();
            System.out.println("\n ya va a recibir el archivo que pesa "+tamanoEnMB);
            //int bytesRead = 0;
            int current = 0;
            int i=0;
            int count;
            while ((count = inFromServer.read(bytes)) > 0) {
                fos.write(bytes, 0, count);
                i++;
                current+=count;
                System.out.println("Escribiendo en el archivo el mensaje "+i+ " "+new String(bytes));
            }

     	   
            System.out.println("File " + tituloAPedir
                + " downloaded (" + current + " bytes read)");
            System.out.println("\n en la <rutadelrepo>/descargas se encuentra el archivo descargado");
            
    	}
    	 catch (Exception e) {
 			e.printStackTrace();
         	cerrarConexion();
         	//borra el file
         	if(file != null)
         	{
         		file.delete();         		
         	}
         	
         	return "Se termino abruptamente la descarga ";
 		} 
    	cerrarConexion();
    	interfaz.terminoDeDescargar();
        return "El archivo fue correctamente descargado en la carpeta ./descargas";
    }
    
    
	/**
	 * Cierra conexión TCP
	 * @throws Exception
	 */

    public void cerrarConexion(){
    	/**
    	 * siempre que termina de descargar algo, cierra conexion
    	 */
        try {
        	estadoConectado = false;
        	if(socket != null)
        	{
        		socket.close();
    			socket = null;
        	}
			
		} catch (IOException e) {
			System.out.println("Se ha detenido la descarga. No se podrá abrir el archivo.");
		}
    	
    }

    
    public void detenerDescarga(){
    	cerrarConexion();
    }
    
    
    public boolean darEstadoConexion(){
    	return estadoConectado;
    }
    
    

}
