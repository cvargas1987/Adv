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

				if (Main.args_caja.equals("0")) {  // Busqueda en todas las cajas
					query_T =("SELECT * FROM transacciones "
							+ "WHERE "+ Main.args_opcion +" BETWEEN " + "'" + Main.args_datoIni+ "'" +" AND " + "'" +Main.args_datoFin+ "'"
							+";");
					
				}else { //Busqueda en una sola caja
					query_T = ("SELECT * FROM transacciones "
								+ "WHERE " + Main.args_opcion + " BETWEEN " + "'" + Main.args_datoIni+ "'" +" AND " + "'" +Main.args_datoFin+ "'"
								+ "AND IdCaja = " + "'"+ Main.args_caja+ "'"  
								+";");
				}
		
		query_I = ("SELECT * FROM transacciones "
					+ "INNER JOIN detalletransacciones "
					+ "ON transacciones.IdTransaccion = detalletransacciones.IdTransaccion "
					+ "WHERE " + Main.args_opcion + " BETWEEN " + "'" + Main.args_datoIni+ "'" +" AND " + "'" +Main.args_datoFin+ "'"+";");

		query_P =("SELECT * FROM transacciones "
					+ "INNER JOIN formapago "
					+ "ON transacciones.IdTransaccion = formapago.IdTransaccion "
					+ "WHERE " +  Main.args_opcion + " BETWEEN " + "'" + Main.args_datoIni+ "'" +" AND " + "'" +Main.args_datoFin+ "'"+";");
		
		System.out.println("Consultado, espere...");
		resultado_T = conexion.getQuery(query_T);
		if (resultado_T.next()) {
			resultado_T = conexion.getQuery(query_T);
			resultado_I = conexion.getQuery(query_I);
			resultado_P = conexion.getQuery(query_P);
			
			file_interfaz_rbo.procesarT(resultado_T);
			file_interfaz_rbo.procesar_I(resultado_I);
			file_interfaz_rbo.procesar_P(resultado_P);
		}else {
			System.out.println("Sin Datos");
		}
		System.out.println("Fin Proceso");
	}
}
