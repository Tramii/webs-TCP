package interfazClient;

import mundoClient.Cliente;

public class VerificadorDeConexion extends Thread {
	private PanelEstadoConexion panelEstado;
	private Cliente cliente;
	public VerificadorDeConexion(PanelEstadoConexion p, Cliente client)
	{
		panelEstado = p;
		cliente = client;
	}
	
	public void run(){
		while(true)
		{
			//cambiarEstadoConexion
			if(cliente != null)
			{
				boolean conexion = cliente.darEstadoConexion();
				panelEstado.cambiarEstadoConexion(conexion);
				System.out.println("revisando estado "+ conexion);
				try {
					//cada 3 segundos revisa si hay conexion
					this.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				cliente = panelEstado.pedirCliente();
				yield();
			}

		}
	}
}
