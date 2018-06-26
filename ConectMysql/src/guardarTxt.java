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
		
		fichero = new FileWriter(Main.args_fileAns);
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
		
		fichero = new FileWriter(Main.args_fileAns, true);
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
					d_Puntos=0	, 
					d_VendedorPorEstructura=0;
			
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
			d_VendedorPorEstructura = resultado.getInt("VendedorPorEstructura");
			
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
					+ tab
					+ d_VendedorPorEstructura
					+ tab);
			
			System.out.println(linea_I);
			pw.println(linea_I);
//			System.out.println("Archivo: " + fwRuta.getAbsolutePath());
		}
		fichero.close();
	}
					
	public void procesar_P (ResultSet resultado) throws SQLException, IOException {
		System.out.println("- > Generando Pagos... "); 
		
		fichero = new FileWriter(Main.args_fileAns, true);
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
	
	public void procesar_cupones (ResultSet resultado) throws SQLException, IOException{
		int IdBase = 0;
		int IdCupon = 0;
		int IdCaja = 0; 
		String Fecha = null;
		int IdCajero = 0; 
		String ImpresoraFiscal = null; 
		int Folio = 0; 
		double MontoNeto = 0; 
		int IdCliente = 0; 
		String ClienteNombre = null; 
		String FechaProceso = null; 
		String lineaC = null; 
		
		fichero = new FileWriter(Main.args_fileAns);
		pw = new PrintWriter(fichero);
		
		while (resultado.next()) {
			IdBase = resultado.getInt("IdBase");
			IdCupon = resultado.getInt("IdCupon");
			IdCaja = resultado.getInt("IdCaja");
			Fecha = resultado.getString("Fecha");
			IdCajero = resultado.getInt("IdCajero");
			ImpresoraFiscal = resultado.getString("ImpresoraFiscal");
			Folio = resultado.getInt("Folio");
			MontoNeto = resultado.getDouble("MontoNeto");
			IdCliente = resultado.getInt("IdCliente");
			ClienteNombre = resultado.getString("ClienteNombre");
			FechaProceso = resultado.getString("FechaProceso");
			
			lineaC = (IdBase 
						+ tab 
						+ IdCaja 
						+ tab
						+ Fecha
						+ tab 
						+ IdCajero
						+ tab
						+ "0" //vendedor
						+ tab
						+ ImpresoraFiscal
						+ tab
						+ Folio
						+ tab
						+ IdCupon //det-transaccion, para el caso de cupon de llevar el idCupon.
						+ tab
						+ "C" //tipo de registro, para el caso de cupon debe ser C
						+ tab
						// hora ini
						+ tab
						// hora fin
						+ tab
						+ "0"		// monto neto
						+ tab
						+ "0"		// impuesto
						+ tab
						// id descto
						+ tab
						+ "0"		// %descto global
						+ tab
						+ IdCliente
						+ tab
						+ ClienteNombre
						+ tab
						//cliente #tajr
						+ tab
						+ "0"		// puntos total
						+ tab
						+ "0"		// puntos total cuenta
						+ tab
						+ "0"		// puntos total fpago
						+ tab
						+ "0"		// puntos total art
						+ tab
						+ "0"		// det fpago
						+ tab
						// id abono
						+ tab
						// hora seg ini
						+ tab
						// hora seg fin
						+ tab
						);
			
			
			System.out.println(lineaC);
			pw.println(lineaC);
		}
		fichero.close();
	}
	
	public void procesar_controlZ (ResultSet resultado) throws SQLException, IOException{
		fichero = new FileWriter(Main.args_fileAns);
		pw = new PrintWriter(fichero);
		String linea_Z = ""; 
		
		int IdControlZ; 
		int IdBase;
		String IdCaja; 
		String ImpresoraFiscal;
		int NumeroZ;
		String FechaInicio;
		String FechaFin;
		int ITransaccion; 
		int IITransaccion; 
		String NombreArchivo;
		String FechaProceso; 
		int ITransaccionNC;
		int IITransaccionNC;
		String StatusImpresora; 
		String StatusFiscal;
		int CantidadFacturas; 
		double MontoFacturas;
		int ITransaccionND;
		int IITransaccionND; 
		int CantidadND; 
		double MontoND; 
		int IDocumentoNF; 
		int IIDocumentoNF; 
		int CantidadDocumentoNF; 
		int UltimoX; 
		int UltimoRepAuditoria; 
		double MontoTotalVentaGrab; 
		double MontoTotalVentaNoGrab; 
		int CantidadNC; 
		double MontoNC ; 
		int CantidadDevolucion; 
		double MontoDevolucion; 
		double MontoNCEmitidas; 
		int CantidadDescuento; 
		double MontoDescuento; 
		double TasaITBMS1; 
		double MontoVentaITBMS1; 
		double MontoImpITBMS1; 
		double TasaITBMS2; 
		double MontoVentaITBMS2; 
		double MontoImpITBMS2; 
		double TasaITBMS3; 
		double MontoVentaITBMS3; 
		double MontoImpITBMS3; 
		double TasaITBMS4; 
		double MontoVentaITBMS4; 
		double MontoImpITBMS4; 
		int CantidadPagoEF; 
		double MontoPagoEF; 
		int CantidadPagoTD; 
		double MontoPagoTD; 
		int CantidadPagoTC;  
		double MontoPagoTC;
		int CantidadPagoCH; 
		double MontoPagoCH;
		int CantidadPagoOP;  
		double MontoPagoOP;
		
		while (resultado.next()) {
		
			 IdControlZ = resultado.getInt("IdControlZ"); 
			 IdBase = resultado.getInt("IdBase");
			 IdCaja = resultado.getString("IdCaja"); 
			 ImpresoraFiscal = resultado.getString("ImpresoraFiscal");
			 NumeroZ = resultado.getInt("NumeroZ");
			 FechaInicio = resultado.getString("FechaInicio");
			 FechaFin = resultado.getString("FechaFin");
			 ITransaccion = resultado.getInt("ITransaccion"); 
			 IITransaccion = resultado.getInt("IITransaccion"); 
			 NombreArchivo = resultado.getString("NombreArchivo");
			 FechaProceso = resultado.getString("FechaProceso"); 
			 ITransaccionNC = resultado.getInt("ITransaccionNC");
			 IITransaccionNC = resultado.getInt("IITransaccionNC");
			 StatusImpresora = resultado.getString("StatusImpresora"); 
			 StatusFiscal = resultado.getString("StatusFiscal");
			 CantidadFacturas = resultado.getInt("CantidadFacturas"); 
			 MontoFacturas = resultado.getDouble("MontoFacturas");
			 ITransaccionND = resultado.getInt("ITransaccionND"); 
			 IITransaccionND = resultado.getInt("IITransaccionND"); 
			 CantidadND = resultado.getInt("CantidadND"); 
			 MontoND = resultado.getDouble("MontoND"); 
			 IDocumentoNF = resultado.getInt("IDocumentoNF"); 
			 IIDocumentoNF = resultado.getInt("IIDocumentoNF"); 
			 CantidadDocumentoNF = resultado.getInt("CantidadDocumentoNF"); 
			 UltimoX = resultado.getInt("UltimoX"); 
			 UltimoRepAuditoria = resultado.getInt("UltimoRepAuditoria"); 
			 MontoTotalVentaGrab = resultado.getDouble("MontoTotalVentaGrab"); 
			 MontoTotalVentaNoGrab = resultado.getDouble("MontoTotalVentaNoGrab"); 
			 CantidadNC = resultado.getInt("CantidadNC"); 
			 MontoNC = resultado.getDouble("MontoNC") ; 
			 CantidadDevolucion = resultado.getInt("CantidadDevolucion"); 
			 MontoDevolucion = resultado.getDouble("MontoDevolucion"); 
			 MontoNCEmitidas = resultado.getDouble("MontoNCEmitidas"); 
			 CantidadDescuento = resultado.getInt("CantidadDescuento"); 
			 MontoDescuento = resultado.getDouble("MontoDescuento"); 
			 TasaITBMS1 = resultado.getDouble("TasaITBMS1"); 
			 MontoVentaITBMS1 = resultado.getDouble("MontoVentaITBMS1"); 
			 MontoImpITBMS1 = resultado.getDouble("MontoImpITBMS1"); 
			 TasaITBMS2 = resultado.getDouble("TasaITBMS2"); 
			 MontoVentaITBMS2 = resultado.getDouble("MontoVentaITBMS2"); 
			 MontoImpITBMS2 = resultado.getDouble("MontoImpITBMS2"); 
			 TasaITBMS3 = resultado.getDouble("TasaITBMS3"); 
			 MontoVentaITBMS3 = resultado.getDouble("MontoVentaITBMS3"); 
			 MontoImpITBMS3 = resultado.getDouble("MontoImpITBMS3"); 
			 TasaITBMS4 = resultado.getDouble("TasaITBMS4"); 
			 MontoVentaITBMS4 = resultado.getDouble("MontoVentaITBMS4"); 
			 MontoImpITBMS4 = resultado.getDouble("MontoImpITBMS4"); 
			 CantidadPagoEF = resultado.getInt("CantidadPagoEF"); 
			 MontoPagoEF = resultado.getDouble("MontoPagoEF"); 
			 CantidadPagoTD = resultado.getInt("CantidadPagoTD"); 
			 MontoPagoTD = resultado.getDouble("MontoPagoTD"); 
			 CantidadPagoTC = resultado.getInt("CantidadPagoTC");  
			 MontoPagoTC = resultado.getDouble("MontoPagoTC");
			 CantidadPagoCH = resultado.getInt("CantidadPagoCH") ; 
			 MontoPagoCH = resultado.getDouble("MontoPagoCH");
			 CantidadPagoOP = resultado.getInt("CantidadPagoOP");  
			 MontoPagoOP = resultado.getDouble("MontoPagoOP");
			
			 linea_Z = ( IdControlZ 
					 		+ tab
							+ IdBase
							+ tab
							+ IdCaja
							+ tab
							+ ImpresoraFiscal
							+ tab
							+  NumeroZ
							+ tab
							+ FechaInicio
							+ tab
							+ FechaFin
							+ tab
							+ ITransaccion
							+ tab
							+ IITransaccion
							+ tab
							+ NombreArchivo
							+ tab
							+ FechaProceso
							+ tab
							+ ITransaccionNC
							+ tab
							+ IITransaccionNC
							+ tab
							+ StatusImpresora
							+ tab
							+ StatusFiscal
							+ tab
							+ CantidadFacturas
							+ tab
							+ MontoFacturas
							+ tab
							+ ITransaccionND
							+ tab
							+ IITransaccionND
							+ tab
							+ CantidadND
							+ tab
							+ MontoND
							+ tab
							+ IDocumentoNF
							+ tab
							+ IIDocumentoNF
							+ tab
							+ CantidadDocumentoNF
							+ tab
							+ UltimoX
							+ tab
							+ UltimoRepAuditoria
							+ tab
							+ MontoTotalVentaGrab
							+ tab
							+ MontoTotalVentaNoGrab
							+ tab
							+ CantidadNC
							+ tab
							+ MontoNC
							+ tab
							+ CantidadDevolucion
							+ tab
							+ MontoDevolucion
							+ tab
							+ MontoNCEmitidas
							+ tab
							+ CantidadDescuento
							+ tab
							+ MontoDescuento
							+ tab
							+ TasaITBMS1
							+ tab
							+ MontoVentaITBMS1
							+ tab
							+ MontoImpITBMS1
							+ tab
							+ TasaITBMS2
							+ tab
							+ MontoVentaITBMS2
							+ tab
							+ MontoImpITBMS2
							+ tab
							+ TasaITBMS3
							+ tab
							+ MontoVentaITBMS3
							+ tab
							+ MontoImpITBMS3
							+ tab
							+ TasaITBMS4
							+ tab
							+ MontoVentaITBMS4
							+ tab
							+ MontoImpITBMS4
							+ tab
							+ CantidadPagoEF
							+ tab
							+ MontoPagoEF
							+ tab
							+ CantidadPagoTD
							+ tab
							+ MontoPagoTD
							+ tab
							+ CantidadPagoTC
							+ tab
							+ MontoPagoTC
							+ tab
							+ CantidadPagoCH
							+ tab
							+ MontoPagoCH
							+ tab
							+ CantidadPagoOP
							+ tab
							+ MontoPagoOP
			 				+ tab);
			
			 	System.out.println(linea_Z);
				pw.println(linea_Z);
	
			
		}
		fichero.close();
	}
	
}


	
