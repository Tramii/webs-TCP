package interfazClient;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
     * Crea la ventana principal de la aplicación
     */
	public InterfazCliente(){

		setLayout(new BorderLayout());
		setSize(480,300);
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
	// Ejecución
	//-----------------------------------------------------------------
	public void iniciarConexion()
	{
		listaFiles = cliente.iniciarConexion();
		panelArchivos.actualizarLabelFiles(listaFiles);
	}
	
	public void cerrarConexion(){
		cliente.cerrarConexion();
	}
	
	public Cliente darCliente(){
		return cliente;
	}
	
	public void descargar(){
		int seleccionado = panelArchivos.darSeleccionado();
		
		if (seleccionado==-1) JOptionPane.showMessageDialog(this, "Se debe seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
		if (seleccionado==0) JOptionPane.showMessageDialog(this, "Se debe seleccionar sólo un archivo", "Error", JOptionPane.ERROR_MESSAGE);
		else if (seleccionado==1) cliente.pedirArchivo("4,84MB.pdf");
		else if (seleccionado==2) cliente.pedirArchivo("29MB.pdf");
		else if (seleccionado==3) cliente.pedirArchivo("90MB.pdf");
		
	}
	
	public void verDescargas(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./descargas"));
		int result = fileChooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION){
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			try {
				java.awt.Desktop.getDesktop().open(selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void detener(){
		cliente.detenerDescarga();
	}
	
	/**
	 * Inicializa la aplicación
	 * @param args argumentos de la aplicación, no se requiere ninguno
	 */

	public static void main(String[] args) {
		InterfazCliente main = new InterfazCliente();
		main.setVisible(true);
	}

}
