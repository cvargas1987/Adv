
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class leerXml {
	
	static String  _ip = null; 
	static String _bd = null;
	static String _us = null;
	static String _pass = null;
	
    public static void leer() {
    	String nombreArchivo = "dato.xml";
    			
//    	ArrayList<datosXml> lista_conexion = new ArrayList <>();
    	//datosXml d= new datosXml();
    	
    	String datos = null; 
    	
    	 try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
//            	System.out.println("Existe el Archivo <" + nombreArchivo  + ">");
            	
            	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                 DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
                 Document document = documentBuilder.parse(archivo);
                 
                 document.getDocumentElement().normalize();
                 
//                 System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
                 
                 NodeList listaParametros = document.getElementsByTagName("conexion");
                 
                 for (int temp = 0; temp <listaParametros.getLength(); temp++) {
                     Node nodo = listaParametros.item(temp);
//                     System.out.println("Elemento:" + nodo.getNodeName());
                     if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                         Element element = (Element) nodo;
                         
                         _ip = element.getElementsByTagName("ipServer").item(0).getTextContent();
                         _bd= element.getElementsByTagName("bd").item(0).getTextContent();
                         _us= element.getElementsByTagName("us").item(0).getTextContent();
                         _pass= element.getElementsByTagName("pass").item(0).getTextContent();
                         
//                         datosXml d = new datosXml(); 
//                         d.setBd(_bd);
//                         d.setIp(_ip);
//                         d.setPass(_pass);
//                         d.setUs(_us);
//                         
//                         lista_conexion.add(d); 
//                         return lista_conexion; 
//                         d.setBd(_bd);
//                         d.setIp(_ip);
//                         
//                         System.out.println("ip: "+_ip);
//                         System.out.println("base de datos: " + _bd);
                         
//                         System.out.println("ip: " + element.getElementsByTagName("ipServer").item(0).getTextContent());
//                         System.out.println("base de datos: " + element.getElementsByTagName("bd").item(0).getTextContent());
                         
                       
                     }
                 }
//                 System.out.println("IP:	" + d.getIp());
//                 System.out.println("BD:	" + d.getBd()); 
            } else
            	System.out.println("ERROR: NO Existe el Archivo <" + nombreArchivo + ">");
            	
            	
    	 } catch (Exception e) {
    		 e.printStackTrace();
    	 }
    	 //return _ip; 
//		return lista_conexion;
    	 
    }
}


                    
                         