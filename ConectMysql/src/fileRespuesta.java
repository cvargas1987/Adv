import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class fileRespuesta {

	private FileWriter fw = null; 
	private PrintWriter pw = null; 
	
	public fileRespuesta () {
		
	}
	
	public void escribir (String fr, String r) throws IOException {
		try {
		fw = new FileWriter(fr);
		pw = new PrintWriter(fw);
		
		}catch (IOException e) {
			e.printStackTrace();
			
		}
		pw.print(r);
		fw.close();
	}

}
