package com.bbva.verint.controller;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.bbva.verint.bean.InputVerint;
import com.bbva.verint.exception.CustomException;
import com.bbva.verint.exception.InvalidAttribute001;
import com.bbva.verint.exception.InvalidJSON004;

public class MetadataController {
	/**
	 * Valida que el formato del json se valido
	 *
	 * @param jsonInString
	 * @return
	 * @throws CustomException
	 */
	private static Logger log = Logger.getLogger(MetadataController.class);

	public static InputVerint mapJsonInputString(String jsonInString) throws CustomException {
		log.info("Inicia mapeo de json input string");
		StringBuilder sb = new StringBuilder();
		
		InputVerint input;
		try {
			input = new ObjectMapper().readValue(jsonInString, InputVerint.class);
		} catch (IOException ex) {
			throw new InvalidJSON004(ex);
		}
		try {
			Validate.notBlank(input.getCustomerId());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [CustomerId]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getContractId());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [ContractId]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getDocumentKey());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [Documentkey]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getContactIdVerint());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [ContactIdVerint]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getNameRecord());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [NameRecord]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getExt());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [Ext]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getIdCertificacion());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getNameRecord())
			.append("|")
			.append("Metadata incompleta [IdCertificacion]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		return input;
	}
}
