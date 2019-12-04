package com.bbva.verint.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	private String codigoError = "000";

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Constructor del objeto.
	 */
	public CustomException() {
		super();
	}

	/**
	 * Metodo ArchivingException <br>
	 *
	 * @param message Mensaje del error
	 * @param cause   Causa del error
	 */
	public CustomException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * Metodo ArchivingException <br>
	 *
	 * @param message Mensaje del error
	 */
	public CustomException(String message) {
		super(message);
	}

	/**
	 * Metodo ArchivingException <br>
	 *
	 * @param cause Causa del error
	 */
	public CustomException(Throwable cause) {
		super(cause);
	}

}
