package mundo;

import java.io.File;

public class Files {
	private File algorit;
	private File python;
	private File coding;

	
	public Files()
	{
		algorit = new File("../../data/4,84MB.pdf");
		python = new File("../../data/29MB.pdf");
		coding = new File("../../data/90MB.pdf");
	}
	
	public String darListaTitulos(){
		return " 4,5MB.pdf 29MB.pdf 90MB.pdf";
	}
	
	public File returnFile(String nombre)
	{
		if(nombre.endsWith("4,84MB.pdf"))
		{
			return algorit;
		}
		if(nombre.endsWith("29MB.pdf"))
		{
			return python;
		}
		if(nombre.endsWith("90MB.pdf"))
		{
			return coding;
		}
		return null;
	}


}
