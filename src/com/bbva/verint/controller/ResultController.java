package com.bbva.verint.controller;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bancomer.pia.dsmngr.DataSourceManager;
import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.dao.AlmacenaDocto;
import com.bbva.verint.dao.ExpedienteDao;
import com.bbva.verint.dao.GeneraArchivos;
import com.bbva.verint.dao.PaginaDao;
import com.bbva.verint.parametros.ParametrosVerint;
import com.bbva.verint.principal.SendDocument;
import com.bbva.verint.principal.Test;
import com.syc.rig.client.RigClientException;

public class ResultController extends DataSourceManager{
	
	private static Logger log = Logger.getLogger(ResultController.class);
	
	public static boolean validaResult(String lineResult) {
		String folioArchiving = null;
		boolean isOk = false;
		
		JSONObject json = new JSONObject(lineResult);
		Object err = json.get("error");
		if (!(err instanceof JSONArray)) {
			throw new RigClientException(format("Archiving regreso: %s", lineResult));
		} else {
			JSONArray error = json.getJSONArray("error");
			if (error.length() > 0) {
				RigClientException mcexc = null;

				for (int index = 0; index < error.length(); index++) {
					json = error.getJSONObject(index);
					if (mcexc == null)
						mcexc = new RigClientException(json.getString("_error_message"));
					else
						mcexc = new RigClientException(json.getString("_error_message"), mcexc);
				}

				
				StringBuilder sb = new StringBuilder();
				sb.append("ERROR002")
				.append("|")
				.append(json.get("nr"))
				.append("|")
				.append(mcexc.getMessage());
				
				GeneraArchivos ga = new GeneraArchivos();
				Date fecha = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("yymmdd");
				String Hoy = formato.format(fecha);
	    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + "ERRORES_VERINT_RESULT_" + Hoy + ".txt", sb.toString());
	    		
	    		
	    		if(mcexc != null)
	    			return isOk;
	    		
//				if (mcexc == null)
//					return isOk;
//					//throw new RigClientException(format("Enviando documento %s", "error-prueba"));
//				else
//					
//					//throw new RigClientException(format("Enviando documento %s","error-prueba"), mcexc);
			}

			Object succ = json.get("success");
			
			if (!(succ instanceof JSONArray)) {
					throw new RigClientException(format("Archiving regreso: %s", json));
			} else {
				JSONArray success = json.getJSONArray("success");
				if (success.length() > 0) {
					json = success.getJSONObject(0);
					
					folioArchiving = format("%s@%s", json.getString("_s3_bucket"), json.getString("_s3_key"));
					isOk = true;
				} else {
					throw new RigClientException(format("Archiving regreso: %s", json));
					}
					GeneraArchivos ga = new GeneraArchivos();
		    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + "Resguardado_VERINT_RESULT.txt", json.toString());
				}
			}
			return isOk;
		}
	
	
	public static boolean registraFolio(JSONObject jsonObj) {
		Connection conn = null;
		String tituloAplicacion, keyIntervener, documentKey, contactIdVerint, dateTime, typeOperation, typeMatrix,
		typeDocument, ext, size, product, cr, customerId, funtion, typeTransact, sha1n, descriptionDocument,
		service, signatureAdviser, contractId, nameRecord, idCertification, phaseOperation;
		
		JSONObject atributo 	= new JSONObject();
		JSONArray atributos 	= new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject json;
		JSONArray success = jsonObj.getJSONArray("success");
		jsonObj = success.getJSONObject(0);
		String folioArchiving = format("%s@%s", jsonObj.getString("_s3_bucket"), jsonObj.getString("_s3_key"));
		ExpedienteDao expDao = new ExpedienteDao();
		try {
			if ("P".equals(ParametrosVerint.AMBIENTE)) {
				conn = getConnectionStatic();
			}else if("T".equals(ParametrosVerint.AMBIENTE)){
				conn = AlmacenaDocto.Buscaconexion();
			}
			
			expDao.actualizaFolioDigitalizacion(conn, folioArchiving);
			
			tituloAplicacion 	= "VERINT";
			folioArchiving      = (String) jsonObj.get("f");
			keyIntervener    	= (String) jsonObj.get("ki");
			documentKey      	= (String) jsonObj.get("dk");
			contactIdVerint  	= (String) jsonObj.get("cv");
			dateTime 			= (String) jsonObj.get("dt");
			typeOperation 		= (String) jsonObj.get("to");
			typeMatrix 			= (String) jsonObj.get("tm");
			typeDocument 		= (String) jsonObj.get("td");
			ext 				= (String) jsonObj.get("e");
			size 				= (String) jsonObj.get("s");
			product 			= (String) jsonObj.get("p");
			cr 					= (String) jsonObj.get("cr");
			customerId 			= (String) jsonObj.get("ci");
			funtion 			= (String) jsonObj.get("fu");
			typeTransact 		= (String) jsonObj.get("tt");
			sha1n 				= (String) jsonObj.get("sha1N");
			descriptionDocument = (String) jsonObj.get("dd");
			service 			= (String) jsonObj.get("se");
			signatureAdviser 	= (String) jsonObj.get("sa");
			contractId 			= (String) jsonObj.get("cid");
			nameRecord 			= (String) jsonObj.get("nr");
			idCertification 	= (String) jsonObj.get("ic");
			phaseOperation 		= (String) jsonObj.get("po");

			VerintBean verint = new VerintBean();
			verint.setTituloAplicacion("VERINT");
			for (int i = 0; i < atributos.length(); i++) {
				atributo = atributos.getJSONObject(i);
				log.info("Insertando al map (CAMPO): " + atributo.getString("campo").toString() + ", (VALOR): "
						+ atributo.getString("valor").toString());

				map.put(atributo.getString("campo").toString(), atributo.getString("valor").toString());
			}

			log.info("LLENANDO BEAN.VERINT");
			verint.setCdAplicacion(8);
			verint.setFolioDigitalizacion(folioArchiving);
			verint.setTituloAplicacion(tituloAplicacion);
			verint.setKeyIntervener(keyIntervener);
			verint.setDocumentKey(documentKey);
			verint.setContactIdVerint(contactIdVerint);
			verint.setDateTime(dateTime);
			verint.setTypeOperation(Integer.parseInt(typeOperation));
			verint.setTypeMatrix(typeMatrix);
			verint.setTypeDocument(typeDocument);
			verint.setExt(ext);
			verint.setSize(Integer.parseInt(size));
			verint.setProduct(Integer.parseInt(product));
			verint.setCr(cr);
			verint.setCustomerId(customerId);
			verint.setFuntion(funtion);
			verint.setTypeTransact(typeTransact);
			verint.setSha1n(sha1n);
			verint.setDescriptionDocument(descriptionDocument);
			verint.setService(service);
			verint.setSignatureAdviser(signatureAdviser);
			verint.setContractId(contractId);
			verint.setNameRecord(nameRecord);
			verint.setIdCertificacion(idCertification);
			verint.setPhaseOperation(phaseOperation);
			
			if (!(jsonObj.getString("ic").isEmpty())) {
				GeneraArchivos.generaJSONDocRuc(verint);
			}
			GeneraArchivos.generaArchivoEU(verint);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
