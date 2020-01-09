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
		InputVerint input;

		
		try {
			input = new ObjectMapper().readValue(jsonInString, InputVerint.class);
			StringBuilder sb = new StringBuilder();

		try {
			Validate.notBlank(input.getKeyIntervener());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getKeyIntervener())		
			.append("|")
			.append("Metadata incompleta [KeyIntervener]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getDocumentKey());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getDocumentKey())
			.append("|")
			.append("Metadata incompleta [DocumentKey]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getContactIdVerint());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getContactIdVerint())
			.append("|")
			.append("Metadata incompleta [ContactIdVerint]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getDateTime());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getDateTime())
			.append("|")
			.append("Metadata incompleta [DateTime]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getTypeOperation());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getTypeOperation())
			.append("|")
			.append("Metadata incompleta [TypeOperation]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getTypeMatrix());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getTypeMatrix())
			.append("|")
			.append("Metadata incompleta [TypeMatrix]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getExt());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getExt())
			.append("|")
			.append("Metadata incompleta [Ext]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getTypeDocument());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getTypeDocument())
			.append("|")
			.append("Metadata incompleta [TypeDocument]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getSize());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getSize())
			.append("|")
			.append("Metadata incompleta [Size]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getProduct());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getProduct())
			.append("|")
			.append("Metadata incompleta [Product]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getCr());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getCr())
			.append("|")
			.append("Metadata incompleta [CR]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getCustomerId());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getCustomerId())
			.append("|")
			.append("Metadata incompleta [CustomerId]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getFuntion());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getFuntion())
			.append("|")
			.append("Metadata incompleta [Funtion]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getTypeTransact());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getTypeTransact())
			.append("|")
			.append("Metadata incompleta [TypeTransact]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getSha1n());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getSha1n())
			.append("|")
			.append("Metadata incompleta [Sha1N]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getDescriptionDocument());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getDescriptionDocument())
			.append("|")
			.append("Metadata incompleta [DescriptionDocument]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		try {
			Validate.notBlank(input.getContractId());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getContractId())
			.append("|")
			.append("Metadata incompleta [ContractId]");
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
			Validate.notBlank(input.getPhaseOperation());
		} catch (Exception e) {
			sb.append("ERROR001")
			.append("|")
			.append(input.getPhaseOperation())
			.append("|")
			.append("Metadata incompleta [PhaseOperation]");
			throw new InvalidAttribute001(sb.toString(), e);
		}
		} catch (IOException e) {
			throw new InvalidJSON004 (e);
		}
		return input;
	}
}
