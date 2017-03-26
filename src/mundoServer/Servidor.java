package mundoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor 
{
	private static int PUERTO= 8089;
	private static int NUM_MAX_CLIENTES =50;
	
	
	private ServerSocket server;
	private ArrayList<ServidorHilo> clientesConectados;
	private ArrayList<ServidorHilo> clientesEsperando;

	public Servidor ()
	{
		try 
		{
			clientesConectados = new ArrayList<ServidorHilo>();
			server = new ServerSocket(PUERTO);
			clientesEsperando= new ArrayList<ServidorHilo>();
			aceptarConexionesTCP();

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				server.close( );
			}
			catch( IOException e )
			{
				e.printStackTrace( );
			}
		}
	}



	public void aceptarConexionesTCP()
	{
		System.out.println("Entra aceptar conexiones");
		while(true)
		{
			Socket cliente = null;
			try
			{
				System.out.println("Esperando conexion");
				cliente = server.accept();
				System.out.println("Llego un cliente");
				//verifica que solo haya ese numero de clientes
				//verifica que clientesConectados no esta reservado por saleUnCliente
				if(clientesConectados.size() <= NUM_MAX_CLIENTES-1)
				{
					//el ultimo parametro es el id para que se pueda eliminar luego
					ServidorHilo nuevo = new ServidorHilo(this, cliente, clientesConectados.size());
					nuevo.start();
					clientesConectados.add(nuevo);
					System.out.println("añadio nuevo cliente");
				}
				else
				{
					ServidorHilo nuevo = new ServidorHilo(this, cliente, -1 );
					clientesEsperando.add(nuevo);
					System.out.println("esta esperando un nuevo cliente");
				}


			}
			catch( Exception e )
			{
				try
				{
					cliente.close( );
				}
				catch( IOException e1 )
				{
					e1.printStackTrace( );
				}
				e.printStackTrace( );
			}

		}


	}
	
	public void saleUnCliente(int id)
	{
		//evita que otro cliente recien llegado entre a conectados de una
		synchronized(clientesConectados)
		{
			clientesConectados.remove(id);
			if(clientesEsperando.size() >0)
			{
				synchronized (clientesEsperando){
					//saca al primero de la fila
					ServidorHilo sale = clientesEsperando.remove(0);
					sale.modificarId(clientesConectados.size());
					sale.start();
					clientesConectados.add(sale);
					System.out.println("entra un cliente que estaba esperando");
				}
			}
		}

	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Servidor server = new Servidor();
	}

}
