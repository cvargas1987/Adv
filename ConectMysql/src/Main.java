import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import java.net.MalformedURLException; 

// args0 = fecha inicial ó v
// args1 = fecha final
// args2 = archivo
// args3 = Tipo de Interfaz 0 = ventas/dev
//									   1 = cupones
// args4 = caja, si es valor "0" son todas las cajas. 

public class Main {
	public static  String fileAns = "";
	
	public static void main (String [] args) throws SQLException, IOException {
		

		String respuesta_ = null; 
		String msnError = null;
		fileRespuesta file_respuesta = new fileRespuesta();
		leerXml xml = new leerXml ();
		xml.leer();
		ConectMysql conexion = new ConectMysql ();
		
		if (args[0].toLowerCase().equals("v"))
		{
			version v = new version();
			v.consultarVersion();
			System.exit(0);
		}
		
		if (args[0].toLowerCase().equals("c"))
		{
			if (conexion.con == null)
				System.out.println("ERROR de Conexion");
			else
				System.out.println("Conexion EXITOSA");
			
			System.exit(0);
		}
		
		fileAns = args[2]; 
		if (args.length < 3) {
			msnError = "ERROR: cantidad invalida de argumentos es menor" + args.length;
			System.out.println(msnError);
			System.exit(0);
		}else if (args.length >5) {
			msnError = "ERROR: cantidad invalida de argumentos  = " + args.length ;
			System.out.println(msnError);
			System.exit(0);
		}

			System.out.println("Parametros: "+args[0] + " " +  args[1 ] + " " +  args[2])  ;			
							
			
			if (conexion.con == null) {
				String respuesta = ("ERROR de Conexion"); 
				System.out.println(respuesta);
				file_respuesta.escribir(args[2], respuesta);
				return; 
			}
			guardarTxt file_interfaz_rbo = new guardarTxt();
			switch (args[3]) {
				case "0": 
					System.out.println("Conexion Exitosa ");
					ResultSet resultado_T ;
					ResultSet resultado_I ;
					ResultSet resultado_P ;
					String encabT = null;
					System.out.println("Consultado Ventas, espere...");
							if (!args[4].equals("0")) {  // UNA CAJA ESPECIFICA
								resultado_T = conexion.getQuery
								("SELECT * FROM transacciones "
								+ "WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"
								+ "AND IdCaja = " + "'"+ args[4]+ "'"  
								+";");
							}else { //TODAS LAS CAJAS
								resultado_T = conexion.getQuery
								("SELECT * FROM transacciones "
								+ "WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"
								+";");
							}
					
					resultado_I = conexion.getQuery
							("SELECT * FROM transacciones "
							+ "INNER JOIN detalletransacciones "
							+ "ON transacciones.IdTransaccion = detalletransacciones.IdTransaccion "
							+ "WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"+";");
					
					resultado_P = conexion.getQuery
							("SELECT * FROM transacciones "
							+ "INNER JOIN formapago "
							+ "ON transacciones.IdTransaccion = formapago.IdTransaccion "
							+ "WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"+";");
					
						if (resultado_T.next()) {
							file_interfaz_rbo.procesarT(resultado_T); 
							file_interfaz_rbo.procesar_I(resultado_I);
							file_interfaz_rbo.procesar_P(resultado_P);
							System.out.println("FIN");
						}else {
							System.out.println("Sin Datos");
						}
							
					
						
					break; 
				
				case "1":
					System.out.println("Consultado Cupones, espere...");
					ResultSet resultado_cupones;
					resultado_cupones = conexion.getQuery("SELECT * FROM cupones WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"+";");
					file_interfaz_rbo.procesar_cupones(resultado_cupones);
					System.out.println("FIN");
					break;
			}
	}
}
		
