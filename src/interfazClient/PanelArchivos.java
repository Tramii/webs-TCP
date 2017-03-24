package interfazClient;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelArchivos extends JPanel{
	
	private InterfazCliente principal;
	
	private JCheckBox archivo1;
	
	private JCheckBox archivo2;
	
	private JCheckBox archivo3;
	
	private JLabel textLabel;
	
	public PanelArchivos(InterfazCliente interfaz){
		principal = interfaz;
		JPanel panelIzq = new JPanel();
		JPanel panelDer = new JPanel();
		
		panelIzq.setLayout(new GridLayout(3,1));		
		setBorder(new TitledBorder("Archivos"));
		
		archivo1 = new JCheckBox("Archivo 1");
		archivo2 = new JCheckBox("Archivo 2");
		archivo3 = new JCheckBox("Archivo 3");
		
		panelIzq.add(archivo1);
		panelIzq.add(archivo2);
		panelIzq.add(archivo3);
		
		textLabel = new JLabel();
		panelDer.add(textLabel);
		
		setLayout(new GridLayout(1,2));
		this.add(panelIzq);
		this.add(panelDer);
	}
	
	public void actualizarLabelFiles(String files){
		
		String [] file = files.split(" ");
		files ="";
		for(int i=0; i< file.length;i++)
		{
			files+= (i+1)+". "+file[i]+"\n \n \n";
		}
		
		textLabel.setText("Los archivos posibles a descargar son: \n"+files);
	}

}
