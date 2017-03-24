package interfazClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PanelIniciarConexion extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private InterfazCliente principal;
	
	
	public PanelIniciarConexion (InterfazCliente interfaz) {
		principal = interfaz;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
