package interfazClient;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelEstadoConexion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private InterfazCliente principal;
	
	private JTextField txtEstado;
	
	public PanelEstadoConexion (InterfazCliente interfaz) {
		principal = interfaz;
		
		setLayout(new GridLayout(1,1));		
		setBorder(new TitledBorder("Estado de la conexi�n"));
		
		txtEstado = new JTextField("No hay conexi�n");
		txtEstado.setEditable(false);
		
		add(txtEstado);
	}


}
