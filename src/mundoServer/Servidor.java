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
			clientesConectados = new ArrayList();
			server = new ServerSocket(PUERTO);
			clientesEsperando= new ArrayList();
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
		while(true)
		{
			Socket cliente = null;
			try
			{
				cliente = server.accept();
				
				//verifica que solo haya ese numero de clientes
				//verifica que clientesConectados no esta reservado por saleUnCliente
				if(clientesConectados.size() <= NUM_MAX_CLIENTES-1)
				{
					//el ultimo parametro es el id para que se pueda eliminar luego
					ServidorHilo nuevo = new ServidorHilo(this, cliente, clientesConectados.size());
					nuevo.start();
					clientesConectados.add(nuevo);
				}
				else
				{
					ServidorHilo nuevo = new ServidorHilo(this, cliente, -1 );
					clientesEsperando.add(nuevo);
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
				}
			}
		}

	}

}
