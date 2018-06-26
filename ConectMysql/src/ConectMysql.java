import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement; 

public class ConectMysql {
	public static  Connection con = null; 
	
	public boolean validarConexion () {
	
	String url = "jdbc:mysql://" + leerXml._ip + "/" + leerXml._bd;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			try{
			System.out.println("Conectando a Base de Datos: <" + url + ">");
			con = DriverManager.getConnection(url,leerXml._us,leerXml._pass);
			}catch(Exception e){
				System.out.println(e);
			}
			if (con != null){
				return true; 
					
			}
		} catch (Exception e){
			e.printStackTrace();
			
		}
		return false;
	}
	


	public ResultSet getQuery (String query)  {
		Statement state = null; 
		ResultSet resultado = null; 
		try{
		state = ConectMysql.con.createStatement (); 
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
