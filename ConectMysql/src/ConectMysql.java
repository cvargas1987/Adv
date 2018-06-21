import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement; 

public class ConectMysql {
	Connection con = null; 
	
	public ConectMysql () {
	
	String url = "jdbc:mysql://" + leerXml._ip + "/" + leerXml._bd;
	 
//	System.out.println("IP: "+_ip);
//	System.out.println("US: "+_us);
//	System.out.println("PASS: "+ _pass);
//	System.out.println("BD: "+_Bd);
//	System.out.println("URL: "+ url);
	
		try {
			Class.forName("com.mysql.jdbc.Driver");

			try{
			System.out.println("Conectando a Base de Datos: <" + url + ">");
			con = DriverManager.getConnection(url,leerXml._us,leerXml._pass);
			}catch(Exception e){
				System.out.println(e);
			}
			if (con != null){
				return; 
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	


	public ResultSet getQuery (String query)  {
		Statement state = null; 
		ResultSet resultado = null; 
		try{
		state = con.createStatement (); 
		resultado = state.executeQuery(query); 
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		return resultado; 
	}
	
	public void setQuery (String query){
		Statement state = null ; 
		try {
		state.execute(query);
		} catch (SQLException e){
		}
	}
}
