package com.bbva.verint.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bbva.verint.bean.VerintBean;

public class GeneraArchivos {
	FileWriter archivo;
	
	public void generaArchivoCntrl(String metadata) {
		String ruta = "C:/Users/xme2648/Documents/Archivo Ctrl y Start/Verint.cntrl";
		String contenido = metadata;
//		File file = new File(ruta);
		
		GeneraArchivos ga = new GeneraArchivos();
		ga.writeInfoInFile(ruta, contenido);
		
//		try {
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			FileWriter fw = new FileWriter(file);
//			BufferedWriter bw = null;
//			bw = new BufferedWriter(fw);
//			
//				bw.write(contenido);
//				bw.newLine();
//			bw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public void CreaArchivoStart(){
		String ruta = "C:/Users/xme2648/Documents/Archivo Ctrl y Start/Verint.start";
        String contenido2 = " ";
        File file = new File(ruta);
        // Si el archivo no existe es creado
        try {  
        	if (!file.exists()) {
        		file.createNewFile();
        }
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = null;
				bw=	new BufferedWriter(fw);
				bw.write(contenido2);
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void generaJSONDocRuc(VerintBean verint) {
		String ruta = "C:/Users/xme2648/Documents/Archivo Ctrl y Start/VerintRuc.txt";
		
		JSONObject jsonRuc = new JSONObject();
		JSONArray listContent = new JSONArray();
		
		jsonRuc.put("version", "MSD");
		jsonRuc.put("processId", "CertifiedString");
		
		
		
		/********DATOS***********/		
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("elementName", "DATOS");
		
		JSONArray jsonListData = new JSONArray();
		
		JSONObject certify = new JSONObject();
		certify.put("id", "CERTIFY");
		certify.put("name", verint.getSha1n());
		jsonListData.put(certify);
		
		
		JSONObject customer = new JSONObject();
		customer.put("id", "customerId");
		customer.put("name", verint.getCustomerId());
		jsonListData.put(customer);

		
		JSONObject contracType = new JSONObject();
		contracType.put("id", "contracType");
		contracType.put("name", "BD-MX-CE-CERTIF-001");
		jsonListData.put(contracType);
		
		mapData.put("listData", jsonListData);
		listContent.put(mapData);
		
		
		/********KEYS**********/
		Map<String, Object> mapKeys = new HashMap<String, Object>();
		mapKeys.put("elementName", "KEYS");
		
		JSONArray jsonListKeys = new JSONArray();
		
		JSONObject relationalId = new JSONObject();
		relationalId.put("id", "relationalId");
		relationalId.put("name", verint.getContractId());
		jsonListKeys.put(relationalId);
		
		
		JSONObject contactId = new JSONObject();
		contactId.put("id", "contactId");
		contactId.put("name", verint.getContactIdVerint());
		jsonListKeys.put(contactId);

		
		JSONObject recordingName = new JSONObject();
		recordingName.put("id", "recordingName");
		recordingName.put("name", verint.getNameRecord());
		jsonListKeys.put(recordingName);
		
		JSONObject recordingExt = new JSONObject();
		recordingExt.put("id", "recordingExt");
		recordingExt.put("name", verint.getExt());
		jsonListKeys.put(recordingExt);
		
		
		JSONObject documentId = new JSONObject();
		documentId.put("id", "documentId");
		documentId.put("name", verint.getDocumentKey());
		jsonListKeys.put(documentId);

		
		JSONObject documentName = new JSONObject();
		documentName.put("id", "documentName");
		documentName.put("name", verint.getDescriptionDocument());
		jsonListKeys.put(documentName);
		
		JSONObject digitalizationId = new JSONObject();
		digitalizationId.put("id", "digitalizationId");
		digitalizationId.put("name", verint.getFolioDigitalizacion());
		jsonListKeys.put(digitalizationId);
		
		
		mapKeys.put("listData", jsonListKeys);
		listContent.put(mapKeys);
		
		
		
		/********SET LISTCONTENT**********/
		jsonRuc.put("listContent", listContent);

		GeneraArchivos ga = new GeneraArchivos();
		ga.writeInfoInFile(ruta, jsonRuc.toString());
		
	}
	
	public void writeInfoInFile(String rutaFile, String informacion) {
		try {
			if(new File(rutaFile).exists() == false) {
				archivo = new FileWriter(new File(rutaFile), false);
			}
			archivo = new FileWriter(new File(rutaFile), true);
			archivo.write(informacion.concat("\r\n"));
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void generaArchivoEU(VerintBean verint) {
		String ruta = "C:/Users/xme2648/Documents/Archivo Ctrl y Start/VerintEU.txt";
		
		JSONObject jsonEU = new JSONObject();
		JSONArray listContent = new JSONArray();
		
		Map<String, Object> mapEU = new HashMap<String, Object>();
		
		JSONObject EU = new JSONObject();
		
		jsonEU.put("dateTime", verint.getDateTime());
		jsonEU.put("ext", verint.getExt());
		jsonEU.put("tipeDocument", verint.getTypeDocument());
		jsonEU.put("descriptionDocument", verint.getDescriptionDocument());
		jsonEU.put("nameRecord", verint.getNameRecord());
		jsonEU.put("product", verint.getProduct());
		jsonEU.put("cR", verint.getCr());
		jsonEU.put("typeOperation", verint.getTypeOperation());
		jsonEU.put("idCertification", verint.getIdCertificacion());
		jsonEU.put("contactIdVerint", verint.getContractId());
		jsonEU.put("sha1n", verint.getSha1n());
		jsonEU.put("folioDigitalizacion", verint.getFolioDigitalizacion());
		jsonEU.put("typeTransact", verint.getTypeTransact());
		jsonEU.put("keyIntervener", verint.getKeyIntervener());
		jsonEU.put("typeMatrix", verint.getTypeMatrix());
		jsonEU.put("size", verint.getSize());
		jsonEU.put("funtion", verint.getFuntion());
		jsonEU.put("documentKey", verint.getDocumentKey());
		jsonEU.put("service", verint.getService());
		jsonEU.put("customerId", verint.getCustomerId());
		jsonEU.put("contractId", verint.getContractId());
		jsonEU.put("phaseOperation", verint.getPhaseOperation());
		jsonEU.put("signatureAdviser", verint.getSignatureAdviser());
		
		mapEU.put("listData", EU);
		listContent.put(mapEU);
		
		jsonEU.put("listContent", listContent);
		
		GeneraArchivos ga = new GeneraArchivos();
		ga.writeInfoInFile(ruta, jsonEU.toString());
		
	}
}



//	public static void main(String[] args) {
//		Map<String , Object> metadata1 = new HashMap<String, Object>();
//		JSONObject myJSonRuc = new JSONObject(metadata1.toString());
//		System.out.println(myJSonRuc.toString());
//}
//}
//
