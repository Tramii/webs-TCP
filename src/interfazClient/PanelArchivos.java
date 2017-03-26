package interfazClient;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelArchivos extends JPanel{
	
	@SuppressWarnings("unused")
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
		
		textLabel = new JLabel("Inicia conexión para ver archivos",SwingConstants.CENTER);
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
			files+= "<br/>"+(i+1)+". "+file[i]+"<br/> ";
		}
		
		textLabel.setText("<html>Archivos disponibles para descargar: <br/>"+files+"</html>");
	}
	
	public int darSeleccionado (){
		
		int seleccionado = -1;
		boolean es1 = archivo1.isSelected();
		boolean es2 = archivo2.isSelected();
		boolean es3 = archivo3.isSelected();
		
		if((es1&&es2)||(es1&&es3)||(es2&&es3)) seleccionado=0;
		
		else if (es1) seleccionado=1;
		else if (es2) seleccionado=2;
		else if (es3) seleccionado=3;
		
		return seleccionado;		
	}

}
