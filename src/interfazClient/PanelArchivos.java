package interfazClient;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelArchivos extends JPanel{
	
	private InterfazCliente principal;
	
	private JCheckBox archivo1;
	
	private JCheckBox archivo2;
	
	private JCheckBox archivo3;
	
	public PanelArchivos(InterfazCliente interfaz){
		principal = interfaz;
		
		setLayout(new GridLayout(3,1));		
		setBorder(new TitledBorder("Archivos"));
		
		archivo1 = new JCheckBox("Archivo 1");
		archivo2 = new JCheckBox("Archivo 2");
		archivo3 = new JCheckBox("Archivo 3");
		
		add(archivo1);
		add(archivo2);
		add(archivo3);
	}

}
