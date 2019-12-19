package com.bbva.verint.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.bbva.verint.bean.InputVerint;
import com.bbva.verint.exception.CustomException;

public class ErrorRucEuController  {

	private static Logger log = Logger.getLogger(ErrorRucEuController.class);

	public static InputVerint mapJsonInputString(JSONObject jsonObj) throws CustomException {
		log.info("Inicia mapeo de json input string");
		StringBuilder sb = new StringBuilder();
		
		InputVerint Input = null;
		 
		sb.append("ERROR003").append("|")
		 .append(Input.getNameRecord()).append("|")
		 .append("Error de estructura JSON");
		
		 
		 return Input;
		 
		 
	}
}
