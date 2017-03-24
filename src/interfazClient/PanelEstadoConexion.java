package interfazClient;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mundoClient.Cliente;

public class PanelEstadoConexion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private InterfazCliente principal;
	
	private JTextField txtEstado;
	
	public PanelEstadoConexion (InterfazCliente interfaz) {
		principal = interfaz;
		
		setLayout(new GridLayout(1,1));		
		setBorder(new TitledBorder("Estado de la conexión"));
		
		txtEstado = new JTextField("No hay conexión");
		txtEstado.setEditable(false);
		
		add(txtEstado);
		
		VerificadorDeConexion verificador = new VerificadorDeConexion(this, interfaz.darCliente());
		verificador.start();
	}
	
	public void cambiarEstadoConexion(boolean nuevoEstado){
		txtEstado.setText(nuevoEstado?"Esta conectado":"No hay conexion");
	}
	public Cliente pedirCliente(){
		return principal.darCliente();
	}


}
