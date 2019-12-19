package com.bbva.verint.controller;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bancomer.pia.dsmngr.DataSourceManager;
import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.dao.ExpedienteDao;
import com.bbva.verint.dao.GeneraArchivos;
import com.bbva.verint.dao.PaginaDao;
import com.bbva.verint.parametros.ParametrosVerint;
import com.bbva.verint.principal.SendDocument;
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

				if(mcexc == null)
					isOk= false;
				
				StringBuilder sb = new StringBuilder();
				sb.append("ERROR002")
				.append("|")
				.append(json.get("nr"))
				.append("|")
				.append(mcexc.getMessage());
				
				GeneraArchivos ga = new GeneraArchivos();
	    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + "ERRORES_RESULTdd/mm/yyyy.txt", sb.toString());
	    		
	    		
//				if (mcexc == null)
//					throw new RigClientException(format("Enviando documento %s", metadata.get("nd")));
//				else
//					throw new RigClientException(format("Enviando documento %s", metadata.get("nd")), mcexc);
			}

			Object succ = json.get("success");
			if (!(succ instanceof JSONArray)) {
				throw new RigClientException(format("Archiving regreso: %s", lineResult));
			} else {
				JSONArray success = json.getJSONArray("success");
				if (success.length() > 0) {
					json = success.getJSONObject(0);
					folioArchiving = format("%s@%s", json.getString("_s3_bucket"), json.getString("_s3_key"));
					isOk = true;
				} else {
					throw new RigClientException(format("Archiving regreso: %s", lineResult));
//					GeneraArchivos ga = new GeneraArchivos();
//		    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + "ERRORES_RESULTdd/mm/yyyy.txt", json.toString());
				}
			}
		}
		
		return isOk;
	}
	
	public static boolean registraFolio(JSONObject informacion) {
		Connection conn = null;
		String tituloAplicacion, keyIntervener, documentKey, contactIdVerint, dateTime, typeOperation, typeMatrix,
		typeDocument, ext, size, product, cr, customerId, funtion, typeTransact, sha1n, descriptionDocument,
		service, signatureAdviser, contractId, nameRecord, idCertification, phaseOperation;
		
		JSONObject atributo 	= new JSONObject();
		JSONArray atributos 	= new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject json;
		JSONArray success = informacion.getJSONArray("success");
		json = success.getJSONObject(0);
		String folioArchiving = format("%s@%s", json.getString("_s3_bucket"), json.getString("_s3_key"));
		ExpedienteDao expDao = new ExpedienteDao();
		try {
			conn = getConnectionStatic();
			expDao.actualizaFolioDigitalizacion(conn, folioArchiving);
			
			tituloAplicacion 	= "VERINT";
			keyIntervener    	= (String) informacion.get("keyIntervener");
			documentKey      	= (String) informacion.get("documentKey");
			contactIdVerint  	= (String) informacion.get("contactIdVerint");
			dateTime 			= (String) informacion.get("dateTime");
			typeOperation 		= (String) informacion.get("typeOperation");
			typeMatrix 			= (String) informacion.get("typeMatrix");
			typeDocument 		= (String) informacion.get("tipeDocument");
			ext 				= (String) informacion.get("ext");
			size 				= (String) informacion.get("size");
			product 			= (String) informacion.get("product");
			cr 					= (String) informacion.get("cR");
			customerId 			= (String) informacion.get("customerId");
			funtion 			= (String) informacion.get("funtion");
			typeTransact 		= (String) informacion.get("typeTransact");
			sha1n 				= (String) informacion.get("sha1n");
			descriptionDocument = (String) informacion.get("descriptionDocument");
			service 			= (String) informacion.get("service");
			signatureAdviser 	= (String) informacion.get("signatureAdviser");
			contractId 			= (String) informacion.get("contractId");
			nameRecord 			= (String) informacion.get("nameRecord");
			idCertification 	= (String) informacion.get("idCertification");
			phaseOperation 		= (String) informacion.get("phaseOperation");

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
			
			GeneraArchivos.generaJSONDocRuc(verint);
			GeneraArchivos.generaArchivoEU(verint);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
