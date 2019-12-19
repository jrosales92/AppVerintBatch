package com.bbva.verint.bean;

import java.text.DateFormat;
import java.util.Date;

public class VerintBean {
	
	private int cdAplicacion;
	private String tituloAplicacion;
	private long idGabinete;
	private int idDocumento;
	private int idCarpeta;
	private int idVersion;
	private int numeroPagina;
	private String folioDigitalizacion;
	private String keyIntervener;
	private String documentKey;
	private String contactIdVerint;
	private String dateTime;
	private int typeOperation;
	private String typeMatrix;
	private String typeDocument;
	private String ext;
	private int size;
	private int product;
	private String cr;
	private String customerId;
	private String funtion;
	private String typeTransact;
	private String date;
	private String sha1n;
	private String descriptionDocument;
	private String service;
	private String signatureAdviser;
	private String contractId;
	private String nameRecord;
	private String idCertificacion;
	private String phaseOperation;
	
	public VerintBean() {
		super();
	}

	public VerintBean(int cdAplicacion, String tituloAplicacion, long idGabinete, int idDocumento, int idCarpeta,
			int idVersion, int numeroPagina, String folioDigitalizacion, String keyIntervener, String documentKey,
			String contactIdVerint, String dateTime, int typeOperation, String typeMatrix, String typeDocument,
			String ext, int size, int product, String cr, String customerId, String funtion, String typeTransact,
			String sha1n, String descriptionDocument, String service, String signatureAdviser, String contractId,
			String nameRecord, String idCertificacion, String phaseOperation) {
		super();
		this.cdAplicacion = cdAplicacion;
		this.tituloAplicacion = tituloAplicacion;
		this.idGabinete = idGabinete;
		this.idDocumento = idDocumento;
		this.idCarpeta = idCarpeta;
		this.idVersion = idVersion;
		this.numeroPagina = numeroPagina;
		this.folioDigitalizacion = folioDigitalizacion;
		this.keyIntervener = keyIntervener;
		this.documentKey = documentKey;
		this.contactIdVerint = contactIdVerint;
		this.dateTime = dateTime;
		this.typeOperation = typeOperation;
		this.typeMatrix = typeMatrix;
		this.typeDocument = typeDocument;
		this.ext = ext;
		this.size = size;
		this.product = product;
		this.cr = cr;
		this.customerId = customerId;
		this.funtion = funtion;
		this.typeTransact = typeTransact;
		this.sha1n = sha1n;
		this.descriptionDocument = descriptionDocument;
		this.service = service;
		this.signatureAdviser = signatureAdviser;
		this.contractId = contractId;
		this.nameRecord = nameRecord;
		this.idCertificacion = idCertificacion;
		this.phaseOperation = phaseOperation;
	}

	public int getCdAplicacion() {
		return cdAplicacion;
	}

	public void setCdAplicacion(int cdAplicacion) {
		this.cdAplicacion = cdAplicacion;
	}

	public String getTituloAplicacion() {
		return tituloAplicacion;
	}

	public void setTituloAplicacion(String tituloAplicacion) {
		this.tituloAplicacion = tituloAplicacion;
	}

	public long getIdGabinete() {
		return idGabinete;
	}

	public void setIdGabinete(long idGabinete) {
		this.idGabinete = idGabinete;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdCarpeta() {
		return idCarpeta;
	}

	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}

	public int getIdVersion() {
		return idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getFolioDigitalizacion() {
		return folioDigitalizacion;
	}

	public void setFolioDigitalizacion(String folioDigitalizacion) {
		this.folioDigitalizacion = folioDigitalizacion;
	}

	public String getKeyIntervener() {
		return keyIntervener;
	}

	public void setKeyIntervener(String keyIntervener) {
		this.keyIntervener = keyIntervener;
	}

	public String getDocumentKey() {
		return documentKey;
	}

	public void setDocumentKey(String documentKey) {
		this.documentKey = documentKey;
	}

	public String getContactIdVerint() {
		return contactIdVerint;
	}

	public void setContactIdVerint(String contactIdVerint) {
		this.contactIdVerint = contactIdVerint;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(int typeOperation) {
		this.typeOperation = typeOperation;
	}

	public String getTypeMatrix() {
		return typeMatrix;
	}

	public void setTypeMatrix(String typeMatrix) {
		this.typeMatrix = typeMatrix;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public String getCr() {
		return cr;
	}

	public void setCr(String cr) {
		this.cr = cr;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFuntion() {
		return funtion;
	}

	public void setFuntion(String funtion) {
		this.funtion = funtion;
	}

	public String getTypeTransact() {
		return typeTransact;
	}

	public void setTypeTransact(String typeTransact) {
		this.typeTransact = typeTransact;
	}

	public String getSha1n() {
		return sha1n;
	}

	public void setSha1n(String sha1n) {
		this.sha1n = sha1n;
	}

	public String getDescriptionDocument() {
		return descriptionDocument;
	}

	public void setDescriptionDocument(String descriptionDocument) {
		this.descriptionDocument = descriptionDocument;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSignatureAdviser() {
		return signatureAdviser;
	}

	public void setSignatureAdviser(String signatureAdviser) {
		this.signatureAdviser = signatureAdviser;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getNameRecord() {
		return nameRecord;
	}

	public void setNameRecord(String nameRecord) {
		this.nameRecord = nameRecord;
	}

	public String getIdCertificacion() {
		return idCertificacion;
	}

	public void setIdCertificacion(String idCertificacion) {
		this.idCertificacion = idCertificacion;
	}

	public String getPhaseOperation() {
		return phaseOperation;
	}

	public void setPhaseOperation(String phaseOperation2) {
		this.phaseOperation = phaseOperation2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VerintBean [cdAplicacion=");
		builder.append(cdAplicacion);
		builder.append(", tituloAplicacion=");
		builder.append(tituloAplicacion);
		builder.append(", idGabinete=");
		builder.append(idGabinete);
		builder.append(", idDocumento=");
		builder.append(idDocumento);
		builder.append(", idCarpeta=");
		builder.append(idCarpeta);
		builder.append(", idVersion=");
		builder.append(idVersion);
		builder.append(", numeroPagina=");
		builder.append(numeroPagina);
		builder.append(", folioDigitalizacion=");
		builder.append(folioDigitalizacion);
		builder.append(", keyIntervener=");
		builder.append(keyIntervener);
		builder.append(", documentKey=");
		builder.append(documentKey);
		builder.append(", contactIdVerint=");
		builder.append(contactIdVerint);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append(", typeOperation=");
		builder.append(typeOperation);
		builder.append(", typeMatrix=");
		builder.append(typeMatrix);
		builder.append(", typeDocument=");
		builder.append(typeDocument);
		builder.append(", ext=");
		builder.append(ext);
		builder.append(", size=");
		builder.append(size);
		builder.append(", product=");
		builder.append(product);
		builder.append(", cr=");
		builder.append(cr);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", funtion=");
		builder.append(funtion);
		builder.append(", typeTransact=");
		builder.append(typeTransact);
		builder.append(", sha1n=");
		builder.append(sha1n);
		builder.append(", descriptionDocument=");
		builder.append(descriptionDocument);
		builder.append(", service=");
		builder.append(service);
		builder.append(", signatureAdviser=");
		builder.append(signatureAdviser);
		builder.append(", contractId=");
		builder.append(contractId);
		builder.append(", nameRecord=");
		builder.append(nameRecord);
		builder.append(", idCertificacion=");
		builder.append(idCertificacion);
		builder.append(", phaseOperation=");
		builder.append(phaseOperation);
		builder.append("]");
		return builder.toString();
	}
	
}
