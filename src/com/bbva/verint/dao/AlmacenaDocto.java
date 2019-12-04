package com.bbva.verint.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bancomer.pia.dsmngr.DataSourceManager;
import com.bbva.verint.archiving.MapArchiving;
import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.parametros.ParametrosVerint;


public class AlmacenaDocto extends DataSourceManager {
	private static Logger log = Logger.getLogger(AlmacenaDocto.class);
	
	public boolean addFile (VerintBean verint) throws Exception{
		Connection conn 	= null;
		boolean msgReturn = false;
		
		ExpedienteDao exp   = new ExpedienteDao();
		if ("P".equals(ParametrosVerint.AMBIENTE)) {
			conn = getConnectionStatic();
		}else if("T".equals(ParametrosVerint.AMBIENTE)){
			conn = Buscaconexion();
		}
		
		try{
			System.out.println("Entrando a crear Expediente");
			Statement st 	= conn.createStatement();
			verint 			= exp.validaExpediente(conn, verint, st);
			Map<String, Object> metadata1 	= new HashMap<String, Object>();
			MapArchiving me 				= new MapArchiving();
			metadata1 						=  me.generaMetadata(verint);
			
//			GeneraArchivos.generaJSONDocRuc(verint);
//			GeneraArchivos.generaArchivoEU(verint);
//			System.out.println(verint);
				
			if (verint.getIdGabinete() != 0 && verint.getIdDocumento()!= 0 && verint.getNumeroPagina() != 0  && verint.getIdVersion() != 0){
				Map<String, Object> metadata 	= new HashMap<String, Object>();
				MapArchiving m 					= new MapArchiving();
				metadata 						=  m.generaMetadata(verint);
				System.out.println("Registro a insertar en archivo.control: " + metadata);
				GeneraArchivos contenido = new GeneraArchivos();
				contenido.generaArchivoCntrl(metadata.toString());
				contenido.CreaArchivoStart();
				msgReturn = true;
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return msgReturn;
	}

	public static Connection Buscaconexion() throws Exception{		
		log.info("Conexion D ------ ");
		Connection conn = null; 
		Class.forName("oracle.jdbc.OracleDriver") ;
		//
		//		PRODUCCION
		//		String cadena="jdbc:oracle:thin:@150.100.151.112:1521/LMSBPMX1";
		//		String user= "ZDBSLMS";
		//		String pass= "ZDBSLMSX12";
		//		
		//		TEST
//		String cadena="jdbc:oracle:thin:@150.50.102.249:1521:lmsbdmx1";
//		String user= "zdbslms";
//		String pass= "xfirma123";
		
//		TEST ATHAN MSD
		String cadena="jdbc:oracle:thin:@150.50.102.249:1521:bmatnd01";
		String user= "ZATNMXD1";
		String pass= "zaqwsX019";
		conn = DriverManager.getConnection(cadena,user, pass);
				
		//CALIDAD

//		String cadena="jdbc:oracle:thin:@150.50.103.198:1521/BMATNC01";
//		String user= "ZATNMXC1";
//		String pass= "zaqwsx019";
//		conn = DriverManager.getConnection(cadena,user, pass);

		conn.setAutoCommit(false);
		return conn;
	}

	private JSONObject GetJson(String  obj){

		JSONObject	regreso = new JSONObject(obj);

		return regreso;
	}


}
