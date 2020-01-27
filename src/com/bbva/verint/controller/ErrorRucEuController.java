package com.bbva.verint.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bbva.verint.bean.InputVerint;
import com.bbva.verint.dao.GeneraArchivos;
import com.bbva.verint.exception.CustomException;
import com.bbva.verint.parametros.ParametrosVerint;

public class ErrorRucEuController  {

	private static Logger log = Logger.getLogger(ErrorRucEuController.class);

	public static String mapJsonInputString(JSONObject jsonObj) throws CustomException {
		log.info("Inicia mapeo de json input string");
		StringBuilder sb = new StringBuilder();
		JSONArray listContent = jsonObj.getJSONArray("listContent");
		JSONObject objListContent = listContent.getJSONObject(1);
		JSONArray listData = objListContent.getJSONArray("listData");
		for (int i = 0; i < listData.length(); i++) {
			JSONObject o = listData.getJSONObject(i);
			if("recordingName".equalsIgnoreCase(o.get("id").toString())){
				sb.append("ERROR003").append("|")
				.append(o.get("name")).append("|")
				.append("Error de estructura JSON");
				
				Date fecha = new Date();
	    		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
	    		String Hoy = formato.format(fecha);
				GeneraArchivos ga = new GeneraArchivos();
	    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "ERRORES_VERINT_RESULT_" + Hoy + ".txt", sb.toString());
				
			}
		}
		return sb.toString();
	}
}
