import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
// programa para ejecutar ventas y devolicion de contado. 

public class consultarContado {
	private static String query_T = "";
	private static String query_I = "";
	private static String query_P = "";
	private static String query_C = "";
	
	public void ejecutar () throws IOException, SQLException {
		
		guardarTxt file_interfaz_rbo = new guardarTxt();
		ConectMysql conexion = new ConectMysql ();
		ResultSet resultado_T ;
		ResultSet resultado_I ;
		ResultSet resultado_P ;
		String encabT = null;
		
		if (Main.args_opcion.equals("fecha")) {
			query_T =("CALL SelectTFecha (" + "'" + Main.args_datoIni + "'" + "," + "'" + Main.args_datoFin + "'" + ")");
			query_I = ("CALL SelectTFechaInnerDet (" + "'" +Main.args_datoIni + "'" +"," + "'" + Main.args_datoFin + "'" + ")");
			query_P =("CALL SelectTFechaInnerPago (" +"'" + Main.args_datoIni + "'" +","+ "'" + Main.args_datoFin + "'" + ")");
		}
		if (Main.args_opcion.equals("folio")) {
			query_T =("CALL SelectTFolio (" + "'" + Main.args_datoIni + "'"+"," + "'" + Main.args_datoFin + "'" + ")");
			query_I = ("CALL SelectTFolioInnerDet (" + "'" +Main.args_datoIni + "'" +","+ "'" + Main.args_datoFin + "'" + ")");
			query_P =("CALL SelectTFolioInnerPago (" +"'" + Main.args_datoIni + "'" +","+ "'" + Main.args_datoFin + "'" + ")");
		}
			
		
		System.out.println("Consultando, espere...");
		resultado_T = conexion.getQuery(query_T);
		if (resultado_T.next()) {
			resultado_T = conexion.getQuery(query_T);
			resultado_I = conexion.getQuery(query_I);
			resultado_P = conexion.getQuery(query_P);
			
			file_interfaz_rbo.procesarT(resultado_T);
			file_interfaz_rbo.procesar_I(resultado_I);
			file_interfaz_rbo.procesar_P(resultado_P);
		}else {
			escribirTexto e = new escribirTexto (); 
			e.ejecutar("Sin Datos");
			
			System.out.println("Sin Datos");
			
		}
		System.out.println("Fin Proceso");
	}
}
