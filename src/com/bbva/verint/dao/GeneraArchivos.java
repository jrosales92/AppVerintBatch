package com.bbva.verint.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.parametros.ParametrosVerint;

public class GeneraArchivos {
	
	FileWriter archivo;
	
	public void generaArchivoCntrl(String metadata) {
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
		String Hoy = formato.format(fecha);
		String ruta = ParametrosVerint.PATHFILES + "verint_D01_" + Hoy + "_Audios_IN.CTRL";
		String contenido = metadata;
		GeneraArchivos ga = new GeneraArchivos();
		ga.writeInfoInFile(ruta, contenido);
	}
	
	public void CreaArchivoStart(){
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
		String Hoy = formato.format(fecha);
		String ruta = ParametrosVerint.PATHFILES + "verint_D01_" + Hoy + "_Audios_IN.START";
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
		String ruta = ParametrosVerint.PATHFILES + "NOM151_AUDIOS_AAMMDD.TXT";
		
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
		contracType.put("id", "contractType");
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
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy_hhmm");
		String Hoy = formato.format(fecha);
		String ruta = ParametrosVerint.PATHFILES + "VERINT_EU_MSD_" + Hoy + ".txt";

		StringBuilder lineEu = new StringBuilder();
		lineEu.append(verint.getDateTime()).append("|")
		.append(verint.getExt()).append("|")
		.append(verint.getTypeDocument()).append("|")
		.append(verint.getDescriptionDocument()).append("|")
		.append(verint.getNameRecord()).append("|")
		.append(verint.getProduct()).append("|")
		.append(verint.getCr()).append("|")
		.append(verint.getTypeOperation()).append("|")
		.append(verint.getIdCertificacion()).append("|")
		.append(verint.getContactIdVerint()).append("|")
		.append(verint.getSha1n()).append("|")
		.append(verint.getFolioDigitalizacion()).append("|")
		.append(verint.getTypeTransact()).append("|")
		.append(verint.getKeyIntervener()).append("|")
		.append(verint.getTypeMatrix()).append("|")
		.append(verint.getSize()).append("|")
		.append(verint.getFuntion()).append("|")
		.append(verint.getDocumentKey()).append("|")
		.append(verint.getService()).append("|")
		.append(verint.getCustomerId()).append("|")
		.append(verint.getContractId()).append("|")
		.append(verint.getPhaseOperation()).append("|")
		.append(verint.getSignatureAdviser()).append("|");
		
		GeneraArchivos ga = new GeneraArchivos();
		ga.writeInfoInFile(ruta, lineEu.toString());
		
	}
}
