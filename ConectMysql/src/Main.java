import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import java.net.MalformedURLException; 

// args0 = opciones de busqueda:
//				 			v = version
//                           c = valida conexion		
//                      Folio = busqueda por folio, argumento 5 y 6  
//					  Fecha = busqueda por fecha, argumento 5 y 6.
// 			  FechaInicio = busqueda por fecha en controlZ
//			  FechaFin = busqueda por fecha en controlZ
//           NumeroZ = busqueda por folio en controlZ
// args1 = archivo
// args2 = Tipo de Interfaz 0 = ventas/dev
//									   1 = cupones
//									   2 = CorteZ contado 
//									   
// args3 = caja, si es valor "0" son todas las cajas. 
// args4 = dato ini
// args5 = dato fin

public class Main {
	
	public static String args_opcion = "";
	public static String args_fileAns = "";
	public static String args_tipoInterfaz = "";
	public static String args_caja = "";
	public static String args_datoIni = "";
	public static String args_datoFin = "";
	public static String fileXml = "vtl.xml";
	
	public static void main (String [] args) throws SQLException, IOException {
		
		String msnError = null;
		args_opcion = args[0];
		fileRespuesta file_respuesta = new fileRespuesta();
		leerXml leerXml = new leerXml ();
		leerXml.ejecutar();
		ConectMysql conexion = new ConectMysql ();
		
		if (args_opcion.toLowerCase().equals("v"))
		{
			version v = new version();
			v.consultarVersion();
			System.exit(0);
		}
		
		if (args_opcion.toLowerCase().equals("c")){
			if (conexion.validarConexion() == false)
				System.out.println("ERROR de Conexion");
			else
				System.out.println("Conexion EXITOSA");
			
			System.exit(0);
		}
		
		
		if (args.length < 3) {
			msnError = "ERROR: cantidad invalida de argumentos es menor" + args.length;
			System.out.println(msnError);
			System.exit(0);
		}else if (args.length >7) {
			msnError = "ERROR: cantidad invalida de argumentos  = " + args.length ;
			System.out.println(msnError);
			System.exit(0);
		}
			
			args_fileAns = args[1];
			args_tipoInterfaz = args[2];
			args_caja = args[3];
			args_datoIni = args[4];
			args_datoFin = args[5];

			if (conexion.validarConexion() == false) {
				String respuesta = ("ERROR de Conexion"); 
				System.out.println(respuesta);
				file_respuesta.escribir(args_fileAns, respuesta);
				return; 
			}
			
			guardarTxt file_interfaz_rbo = new guardarTxt();
			switch (args_tipoInterfaz) {
				case "0": //Ventas/dev de contado
					System.out.println("Consultado Ventas y Dev Contado, espere...");
					consultarContado consultarContado = new consultarContado (); 
					consultarContado.ejecutar();
					break; 
				
				case "1": // Cupones
					System.out.println("Consultado Cupones, espere...");
					ResultSet resultado_cupones;
					resultado_cupones = conexion.getQuery("SELECT * FROM cupones WHERE fecha BETWEEN " + "'" + args_datoIni+ "'" +" AND " + "'" +args[1]+ "'"+";");
					file_interfaz_rbo.procesar_cupones(resultado_cupones);
					System.out.println("FIN");
					break;
					
				case "2": // Corte Z contado  
					System.out.println("Consultado controlZ, espere...");
					consultarCorteZ consultarCorteZ = new consultarCorteZ();
					consultarCorteZ.ejecutar();
					break;
						
			}
	}
}
		
