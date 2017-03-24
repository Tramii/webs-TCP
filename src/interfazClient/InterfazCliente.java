package interfazClient;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import javafx.scene.layout.Border;
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
     * Panel para desplegar estado de la conexion
     */
	private PanelEstadoConexion panelEstado;
	
	/**
     * Panel para desplegar botones
     */
	private PanelBotones panelBotones;
	
	/**
     * Panel para desplegar archivos disponibles
     */
	private PanelArchivos panelArchivos;
	
	private String listaFiles;

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea la ventana principal de la aplicaci�n
     */
	public InterfazCliente(){

		setLayout(new BorderLayout());
		setSize(550,300);
		setResizable(false);
		setTitle( "Cliente TCP" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Incializo panel
		panelEstado = new PanelEstadoConexion(this);
		panelBotones= new PanelBotones(this);
		panelArchivos = new PanelArchivos(this);
		
		//Agrego a la interfaz
		add( panelEstado, BorderLayout.NORTH );
		add( panelArchivos, BorderLayout.CENTER );
		add( panelBotones, BorderLayout.SOUTH );
		
		cliente = new Cliente();
		listaFiles= "No se ha establecido conexion";
	}

	//-----------------------------------------------------------------
	// Ejecuci�n
	//-----------------------------------------------------------------
	public void iniciarConexion()
	{
		listaFiles = cliente.iniciarConexion();
		panelArchivos.actualizarLabelFiles(listaFiles);
	}
	public Cliente darCliente(){
		return cliente;
	}
	public void descargar(){
		//todo
	}
	public void detener(){
		//todo
	}
	public void continuar(){
		//todo
	}
	/**
	 * Inicializa la aplicaci�n
	 * @param args argumentos de la aplicaci�n, no se requiere ninguno
	 */

	public static void main(String[] args) {
		InterfazCliente main = new InterfazCliente();
		main.setVisible(true);
	}

}
