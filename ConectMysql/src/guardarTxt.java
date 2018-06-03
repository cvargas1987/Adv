import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

class guardarTxt {
	
	String 	linea_T = null,
				linea_I = null,
				linea_P = null,
				tab = "\t";
	
	FileWriter fichero = null;
    PrintWriter pw = null;
    

	public guardarTxt() throws IOException {
		 
		
	}
	String 	ImpresoraFiscal = null,
			Fecha = null,
			HoraFin= null,
			HoraInicio= null,
			ClienteNombre=null,
			HoraInicioAux= null,
			HoraFinAux=null,
			Transaccion=null,
			ContratoAbono=null, 
			lineaEncab = null;
	int 	Caja = 0,
			IdCajero= 0, 
			Folio=0, 
			idBase,
			IdDescto,
			IdCliente,
			IdVendedor,
			PuntosTotal,
			PuntosCuenta,
			PuntosTotalPago,
			PuntosArt,
			PuntosPago, 
			nivelSatisfaccion;

double MontoNeto = 0,  
			Impuesto=0,  
			PorDesctoGlobal=0;
	
	public String procesarT (ResultSet resultado) throws SQLException, IOException {
		
		System.out.println("- > Generando Encabezado... ");
		
		fichero = new FileWriter(Main.fileAns);
		pw = new PrintWriter(fichero); 
		
		while (resultado.next()) {
			
			idBase = resultado.getInt("IdBase");
			Caja = resultado.getInt("IdCaja");
			Fecha = resultado.getString("Fecha");
			IdCajero = resultado.getInt("IdCajero");
			IdVendedor = resultado.getInt("IdVendedor");
			ImpresoraFiscal = resultado.getString("ImpresoraFiscal");
			Folio = resultado.getInt("Folio"); 
			Transaccion = resultado.getString("Transaccion");
			HoraInicio = resultado.getString("HoraInicio");
			HoraFin = resultado.getString("HoraFin");
			MontoNeto = resultado.getDouble("MontoNeto");
			Impuesto = resultado.getDouble("Impuesto");
			IdDescto = resultado.getInt("IdDescto"); 
			PorDesctoGlobal = resultado.getDouble("PorDesctoGlobal");
			IdCliente = resultado.getInt("IdCliente");
			ClienteNombre = resultado.getString("ClienteNombre");
			PuntosTotal = resultado.getInt("PuntosTotal");
			PuntosCuenta = resultado.getInt("PuntosCuenta");
			PuntosTotalPago = resultado.getInt("PuntosTotalPago");
			PuntosArt = resultado.getInt("PuntosArt");
			PuntosPago = resultado.getInt("PuntosPago");
			ContratoAbono = resultado.getString("ContratoAbono"); 
			HoraInicioAux = resultado.getString("HoraInicioAux");
			HoraFinAux = resultado.getString("HoraFinAux");
			nivelSatisfaccion = resultado.getInt("nivelSatisfaccion");
			
			 lineaEncab = (
					idBase 
					+ tab 
					+ Caja 
					+ tab
					+ Fecha
					+ tab 
					+ IdCajero
					+ tab
					+ IdVendedor
					+ tab
					+ ImpresoraFiscal
					+ tab
					+ Folio
					+ tab
					+ Transaccion
					);

			linea_T = (
							  lineaEncab 
							+ tab
							+ "T"
							+ tab
							+ HoraInicio
							+ tab
							+ HoraFin
							+ tab
							+ MontoNeto
							+ tab
							+ Impuesto
							+ tab
							+ IdDescto
							+ tab
							+ PorDesctoGlobal
							+ tab
							+ IdCliente
							+ tab
							+ ClienteNombre
							+ tab
							+ tab
							+ PuntosTotal
							+ tab
							+ PuntosCuenta
							+ tab
							+ PuntosTotalPago
							+ tab
							+ PuntosArt
							+ tab
							+ PuntosPago
							+ tab
							+ ContratoAbono
							+ tab
							+ HoraInicioAux
							+ tab
							+ HoraFinAux
							+ tab
							+ nivelSatisfaccion
							+ tab
					);
			
//			System.out.println("Archivo: " + fichero.getAbsolutePath());
			System.out.println(linea_T);
			pw.println(linea_T);
			
		}
		fichero.close();
		return (lineaEncab);
	}
					
	public void procesar_I (ResultSet resultado) throws SQLException, IOException {
		
		System.out.println("- > Generando Detalles... "); 
		
		fichero = new FileWriter(Main.fileAns, true);
		pw = new PrintWriter(fichero);

		String d_CodigoBarra = null,
				  d_Unidad = null,
				  linea_I = null;
					
		double d_Cantidad = 0,
					d_Precio= 0,
					d_PrecioPos=0,
					d_PorDesctoLinea=0,
					d_MontoDescto=0,
					d_MontoNeto=0,
					d_CostoUnd=0;
			int		d_CantidadUnidad,
					d_Puntos=0	; 
			
		while (resultado.next()){
			d_CodigoBarra = resultado.getString("CodigoBarra");
			d_Cantidad = resultado.getDouble("Cantidad");
			d_PrecioPos = resultado.getDouble("PrecioPos");
			d_PorDesctoLinea = resultado.getDouble("PorDesctoLinea");
			d_MontoDescto = resultado.getDouble("MontoDescto");
			d_MontoNeto = resultado.getDouble("MontoNeto");
			d_CostoUnd = resultado.getDouble("CostoUnd");
			d_Puntos = resultado.getInt("Puntos");
			d_Unidad = resultado.getString("Unidad");
			d_CantidadUnidad = resultado.getInt("CantidadUnidad");
			
			idBase = resultado.getInt("IdBase");
			Caja = resultado.getInt("IdCaja");
			Fecha = resultado.getString("Fecha");
			IdCajero = resultado.getInt("IdCajero");
			IdVendedor = resultado.getInt("IdVendedor");
			ImpresoraFiscal = resultado.getString("ImpresoraFiscal");
			Folio = resultado.getInt("Folio"); 
			Transaccion = resultado.getString("Transaccion");

			lineaEncab = (
					idBase 
					+ tab 
					+ Caja 
					+ tab
					+ Fecha
					+ tab 
					+ IdCajero
					+ tab
					+ IdVendedor
					+ tab
					+ ImpresoraFiscal
					+ tab
					+ Folio
					+ tab
					+ Transaccion
					);
			
			linea_I =(  
					lineaEncab
					+ tab
					+"I"
					+ tab
					+ d_CodigoBarra
					+ tab
					+ d_Cantidad
					+ tab
					+ d_Precio
					+ tab
					+ d_PrecioPos
					+ tab
					+ d_PorDesctoLinea
					+ tab
					+d_MontoDescto
					+ tab
					+ d_MontoNeto
					+ tab
					+ d_CostoUnd
					+ tab
					+ d_Puntos
					+ tab
					+ d_Unidad
					+ tab
					+ d_CantidadUnidad
					+ tab);
			
			System.out.println(linea_I);
			pw.println(linea_I);
//			System.out.println("Archivo: " + fwRuta.getAbsolutePath());
		}
		fichero.close();
	}
					
	public void procesar_P (ResultSet resultado) throws SQLException, IOException {
		System.out.println("- > Generando Pagos... "); 
		
		fichero = new FileWriter(Main.fileAns, true);
		pw = new PrintWriter(fichero);

		int 	p_IdTransaccion = 0, 
				p_TipoPago=0, 
				p_PuntosPago = 0,
				p_POSPlazo = 0;
				
		double	p_MontoPago,
					p_MontroCobrado = 0,
					p_Retencion,
					p_SubtotalReten;
		String	p_NombreTarjeta, 
					p_NumeroTarjeta, 
					p_POSPlan,
					p_POSFolio,
					p_POSAutorizacion;
		
		while (resultado.next()) {
			p_MontoPago = resultado.getDouble("MontoPago");
			p_Retencion = resultado.getDouble("Retencion");
			p_SubtotalReten = resultado.getDouble("SubtotalReten");
			p_NombreTarjeta = resultado.getString("NombreTarjeta");
			p_NumeroTarjeta = resultado.getString("NumeroTarjeta");
			p_POSPlan = resultado.getString("POSPlan");
			p_POSFolio = resultado.getString("POSFolio");
			p_POSAutorizacion = resultado.getString("POSAutorizacion");
			p_PuntosPago = resultado.getInt("PuntosPago");
			p_POSPlazo=resultado.getInt("POSPlazo"); 
			
			idBase = resultado.getInt("IdBase");
			Caja = resultado.getInt("IdCaja");
			Fecha = resultado.getString("Fecha");
			IdCajero = resultado.getInt("IdCajero");
			IdVendedor = resultado.getInt("IdVendedor");
			ImpresoraFiscal = resultado.getString("ImpresoraFiscal");
			Folio = resultado.getInt("Folio"); 
			Transaccion = resultado.getString("Transaccion");
			
			lineaEncab = (
					idBase 
					+ tab 
					+ Caja 
					+ tab
					+ Fecha
					+ tab 
					+ IdCajero
					+ tab
					+ IdVendedor
					+ tab
					+ ImpresoraFiscal
					+ tab
					+ Folio
					+ tab
					+ Transaccion
					);
		
			linea_P = (
					lineaEncab
					+ tab
					+ "P"
					+ tab
					+ p_IdTransaccion
					+ tab 
					+ p_MontoPago
					+ tab
					+ p_PuntosPago
					+ tab
					+ p_NombreTarjeta
					+ tab
					+ p_NumeroTarjeta
					+ tab
					+ p_POSPlan
					+ tab
					+ p_POSPlazo
					+ tab
					+ p_Retencion
					+ tab
					+ p_SubtotalReten
					+ tab
					+ p_POSFolio
					+ tab
					+ p_POSAutorizacion
					+ tab
					+ p_MontroCobrado
					+ tab);
			System.out.println(linea_P);
			pw.println(linea_P);
			
		}
		fichero.close();
//		System.out.println("Archivo: " + fichero.getAbsolutePath());
	}
}

//				pw.println(lineaT);
//				pw.println(lineaI);
//				pw.println(lineaP); 
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//		System.out.println("Archivo: " + fwRuta.getAbsolutePath());
//	}
	
	
	
	
