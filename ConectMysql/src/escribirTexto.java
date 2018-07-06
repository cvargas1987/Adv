import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class escribirTexto {
	public void ejecutar (String datoIn) throws IOException {
		
	    FileWriter fichero = new FileWriter(Main.args_fileAns);
		PrintWriter pw = new PrintWriter(fichero); 
		pw.println(datoIn);
		fichero.close();
	}
}
