package mundoClient;

public class VerificadorTimeOut extends Thread{
	
	private Cliente cliente;
	public VerificadorTimeOut(Cliente c)
	{
		cliente =c;
	}
	
	public void run(){
		//duerme un minuto
		try {
			this.sleep(1000*60);
			if(!cliente.inicioDescargaDocumento())
			{
				cliente.cerrarConexion();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
