package interfazClient;

import java.awt.Color;
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
		
		txtEstado = new JTextField("");
		txtEstado.setHorizontalAlignment(JTextField.CENTER);
		txtEstado.setEditable(false);
		
		add(txtEstado);
		
		VerificadorDeConexion verificador = new VerificadorDeConexion(this, interfaz.darCliente());
		verificador.start();
	}
	
	public void cambiarEstadoConexion(boolean nuevoEstado){
		txtEstado.setText(nuevoEstado?"Está conectado":"No hay conexión");
		txtEstado.setBackground(nuevoEstado?Color.GREEN:Color.RED);
	}
	
	public Cliente pedirCliente(){
		return principal.darCliente();
	}


}
