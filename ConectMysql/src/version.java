import java.io.IOException;

public class version {
	
	fileRespuesta file_respuesta = new fileRespuesta();
	
	public version () {
	
		
	}
	
	public String consultarVersion () throws IOException {
		String version = "Version Actual: 1.0.0.6"; 
		System.out.println(version);
		System.out.println("V. 1.0.0.1: Salida a Laboratorio");
		System.out.println("V. 1.0.0.2: Crea interfaz de transacciones T de acuerdo a interfaz DET.*.txt de RBO");
		System.out.println("V. 1.0.0.3: mejora rutinas internas, cambia variables estaticas");
		System.out.println("V. 1.0.0.4: agrega parametro para consultar por rango de fechas.");
		System.out.println("V. 1.0.0.5: Adecuacion para agregar registros P, I. .");
		System.out.println("V. 1.0.0.6: Corrige error en query y logica de guardarTxt");
		return version;
	}
}
	

