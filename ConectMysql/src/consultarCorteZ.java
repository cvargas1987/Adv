import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultarCorteZ {

	public void ejecutar() throws IOException, SQLException {
		ConectMysql conexion = new ConectMysql ();
		guardarTxt file_interfaz_rbo = new guardarTxt();
		String query_Z = "";
		String opcion = Main.args_opcion; 
		ResultSet resultado_Z ;
		if (Main.args_opcion.contains("Fecha"))
			opcion = ("DATE(" + Main.args_opcion + ")");
			
		if (Main.args_caja  == "0") { // TODAS las cajas
			query_Z = ( "SELECT * FROM controlz"
							+ " WHERE " +  opcion 
							+ " BETWEEN " + "'"+ Main.args_datoIni +"'" 
							+ " AND " + "'" +Main.args_datoFin + "'"  
							+ " AND IdCaja = " + "'" +Main.args_caja+ "'" +";"  );
		}else { // una caja cajas en especifico
			query_Z = ( "SELECT * FROM controlz"
					+ " WHERE " +   opcion
					+ " BETWEEN " + "'"+ Main.args_datoIni +"'" 
					+ " AND " + "'" +Main.args_datoFin + "';"); 
					
		}

		resultado_Z = conexion.getQuery(query_Z);
		if (resultado_Z.next()) {
			resultado_Z = conexion.getQuery(query_Z);
			file_interfaz_rbo.procesar_controlZ(resultado_Z);
			
		}else {
			System.out.println("Sin Datos");
		}
		System.out.println("Fin Proceso");
				
		
		
	}

}
