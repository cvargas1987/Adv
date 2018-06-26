import java.io.IOException;

public class version {
	
	fileRespuesta file_respuesta = new fileRespuesta();
	
	public version () {
	
		
	}
	
	public String consultarVersion () throws IOException {
		String version = "Version Actual: 1.0.0.12"; 
		System.out.println(version);
		System.out.println("V. 1.0.0.1: Salida a Laboratorio");
		System.out.println("V. 1.0.0.2: Crea interfaz de transacciones T de acuerdo a interfaz DET.*.txt de RBO");
		System.out.println("V. 1.0.0.3: mejora rutinas internas, cambia variables estaticas");
		System.out.println("V. 1.0.0.4: agrega parametro para consultar por rango de fechas.");
		System.out.println("V. 1.0.0.5: Adecuacion para agregar registros P, I. .");
		System.out.println("V. 1.0.0.6: Corrige error en query y logica de guardarTxt");
		System.out.println("V. 1.0.0.7: Corrige error en interfaz de pagos");
		System.out.println("V. 1.0.0.8: Agrega campo para nivel de Satisfaccion");
		System.out.println("V. 1.0.0.9: Corrige bug");
		System.out.println("V. 1.0.0.10: Crea nuvo argumento para apuntar a tabla de cupones.");
		System.out.println("V. 1.0.0.11: Agrega campo en registro I <VendedorPorEstructura>.");
		System.out.println("V. 1.0.0.12: Agrega opciones para buscar en controlZ y mas parametros de filtros en ventas, NUEVA interfaz texto para Z contado.");
		
		return version;
	}
}
	

