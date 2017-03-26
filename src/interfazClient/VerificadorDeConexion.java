 package interfazClient;

import mundoClient.Cliente;

public class VerificadorDeConexion extends Thread {
	private PanelEstadoConexion panelEstado;
	public VerificadorDeConexion(PanelEstadoConexion p)
	{
		panelEstado = p;
	}
	
	public void run(){
		int contador =0;
		while(true)
		{
			//cambiarEstadoConexion
			if(panelEstado.pedirCliente() != null)
			{
				boolean conexion = panelEstado.pedirCliente().darEstadoConexion();
				panelEstado.cambiarEstadoConexion(conexion);
				System.out.println("Conexión abierta:"+ conexion);
				try {
					//cada 3 segundos revisa si hay conexion
					VerificadorDeConexion.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
