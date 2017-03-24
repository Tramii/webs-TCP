package interfazClient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelBotones extends JPanel implements ActionListener{
	
	private final static String INICIAR = "Iniciar conexión";
	private final static String DESCARGAR = "Descargar archivo";
	private final static String DETENER = "Detener descarga";
	private final static String CONTINUAR = "Continuar descarga";


	private InterfazCliente principal;
	
	private JPanel subpanel;
	
	private JButton btIniciar;
	
	private JButton btDescargar;
	
	private JButton btDetener;
	
	private JButton btContinuar;
	
	public PanelBotones(InterfazCliente interfaz){
		
		principal = interfaz;
		
		setLayout(new GridLayout(2,1));		
		setBorder(new TitledBorder("Botoncitos"));
		
		subpanel = new JPanel();
		subpanel.setLayout(new GridLayout(1,3));
		
		btIniciar = new JButton();
		btIniciar.setText(INICIAR);
		btIniciar.setActionCommand(INICIAR);
		btIniciar.addActionListener(this);
		
		btDescargar = new JButton();
		btDescargar.setText(DESCARGAR);
		btDescargar.setActionCommand(DESCARGAR);
		btDescargar.addActionListener(this);
		
		btDetener = new JButton();
		btDetener.setText(DETENER);
		btDetener.setActionCommand(DETENER);
		btDetener.addActionListener(this);
		
		btContinuar = new JButton();
		btContinuar.setText("Otro botón");
		btContinuar.setActionCommand(CONTINUAR);
		btContinuar.addActionListener(this);
		
		add(btIniciar);
		add(subpanel);
		subpanel.add(btDescargar);
		subpanel.add(btDetener);
		subpanel.add(btContinuar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
