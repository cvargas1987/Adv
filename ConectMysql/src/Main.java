import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import java.net.MalformedURLException; 

// args0 = fecha inicial ó v
// args1 = fecha final
// args2 = archivo

public class Main {
	public static  String fileAns = "";
	
	public static void main (String [] args) throws SQLException, IOException {
		
		fileAns = args[2]; 
		String respuesta_ = null; 
		String msnError = null;
		fileRespuesta file_respuesta = new fileRespuesta();
		
		if (args[0].toLowerCase().equals("v"))
		{
			version v = new version();
			v.consultarVersion();
			System.exit(0);
		}
		
		if (args.length < 3) {
			msnError = "ERROR: cantidad invalida de argumentos";
			System.out.println(msnError);
			System.exit(0);
		}else if (args.length >3) {
			msnError = "ERROR: cantidad invalida de argumentos";
			System.out.println(msnError);
			System.exit(0);
		}
		
			
			
			System.out.println("Parametros: "+args[0] + " " +  args[1 ] + " " +  args[2])  ;			
			leerXml xml = new leerXml ();
			xml.leer();
							
			ConectMysql conexion = new ConectMysql ();
			if (conexion.con != null){
				System.out.println("Conexion Exitosa ");
				ResultSet resultado_T ;
				ResultSet resultado_I ;
				ResultSet resultado_P ;
				String encabT = null;
			
				System.out.println("Ejecutando Qery, espere...");
				
				resultado_T = conexion.getQuery("SELECT * FROM transacciones WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"+";");
				resultado_I = conexion.getQuery("SELECT * FROM transacciones INNER JOIN detalletransacciones ON transacciones.IdTransaccion = detalletransacciones.IdTransaccion WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"+";");
				resultado_P = conexion.getQuery("SELECT * FROM transacciones INNER JOIN formapago ON transacciones.IdTransaccion = formapago.IdTransaccion WHERE fecha BETWEEN " + "'" + args[0]+ "'" +" AND " + "'" +args[1]+ "'"+";");
				
				
				guardarTxt file_interfaz_rbo = new guardarTxt();
				encabT = file_interfaz_rbo.procesarT(resultado_T); 
				file_interfaz_rbo.procesar_I(resultado_I, encabT);
				file_interfaz_rbo.procesar_P(resultado_P, encabT);
				
				System.out.println("FIN");

			}else {
				String respuesta = ("ERROR de Conexion"); 
				System.out.println(respuesta);
				try
				{
					file_respuesta.escribir(args[2], respuesta);
				} catch (IOException e) 
				   {e.printStackTrace();}
		}
	}
}
		
