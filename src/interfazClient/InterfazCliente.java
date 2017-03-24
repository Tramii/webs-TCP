package interfazClient;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import mundoClient.Cliente;

public class InterfazCliente extends JFrame{

	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
     * Cliente
     */
    private Cliente cliente;
	
	/**
     * Panel para desplegar boton de inicio de conexion
     */
	private PanelIniciarConexion panelIniciarCon;


	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea la ventana principal de la aplicación
     */
	public InterfazCliente(){

		setLayout(new BorderLayout());
		setSize(550,530);
		setResizable(false);
		setTitle( "Cliente TCP" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Incializo panel
		panelIniciarCon = new PanelIniciarConexion(this);
		
		//Agrego a la interfaz
		add( panelIniciarCon, BorderLayout.NORTH );
		
		//cliente = new Cliente();
	}

	//-----------------------------------------------------------------
	// Ejecución
	//-----------------------------------------------------------------

	/**
	 * Inicializa la aplicación
	 * @param args argumentos de la aplicación, no se requiere ninguno
	 */

	public static void main(String[] args) {
		InterfazCliente main = new InterfazCliente();
		main.setVisible(true);
	}

}
