package interfazClient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelBotones extends JPanel implements ActionListener{
	
	private final static String INICIAR = "Iniciar conexión";
	private final static String CERRAR = "Cerrar conexión";
	private final static String DESCARGAR = "Descargar archivo";
	private final static String DETENER = "Detener descarga";
	private final static String CONTINUAR = "Continuar descarga";


	private InterfazCliente principal;
	
	private JPanel subpanel1;
	
	private JPanel subpanel2;
	
	private JButton btIniciar;
	
	private JButton btCerrar;
	
	private JButton btDescargar;
	
	private JButton btDetener;
	
	private JButton btContinuar;
	
	public PanelBotones(InterfazCliente interfaz){
		
		principal = interfaz;
		
		setLayout(new GridLayout(2,1));		
		setBorder(new TitledBorder("Botoncitos"));
		
		subpanel1 = new JPanel();
		subpanel1.setLayout(new GridLayout(1, 2));
		
		subpanel2 = new JPanel();
		subpanel2.setLayout(new GridLayout(1,3));
		
		btIniciar = new JButton();
		btIniciar.setText(INICIAR);
		btIniciar.setActionCommand(INICIAR);
		btIniciar.addActionListener(this);
		
		btCerrar = new JButton();
		btCerrar.setText(CERRAR);
		btCerrar.setActionCommand(CERRAR);
		btCerrar.addActionListener(this);
		
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
		
		add(subpanel1);
		add(subpanel2);
		
		subpanel1.add(btIniciar);
		subpanel1.add(btCerrar);
		
		subpanel2.add(btDescargar);
		subpanel2.add(btDetener);
		subpanel2.add(btContinuar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		if(comando.equals(INICIAR)){
			principal.iniciarConexion();
		}
		
		else if (comando.equals(CERRAR)){
			principal.cerrarConexion();
		}
		
		else if (comando.equals(DESCARGAR)){
			principal.descargar();
		}	
		
		
		else if(comando.equals(DETENER)){
			principal.detener();
		}
		
		else if(comando.equals(CONTINUAR)){
			principal.continuar();
		}
		
	}

}
