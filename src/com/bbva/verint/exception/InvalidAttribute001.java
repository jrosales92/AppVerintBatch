package com.bbva.verint.exception;

public class InvalidAttribute001 extends CustomException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor del objeto.
	 */
	public InvalidAttribute001() {
		super();
	}

	/**
	 * Metodo ArchivingException <br>
	 *
	 * @param message
	 * @param cause   Causa del error
	 */
	public InvalidAttribute001(String message, Throwable cause) {
		super(message, cause);
		super.setCodigoError("001");
	}

	public InvalidAttribute001(String message) {
		super(message);
		super.setCodigoError("001");
	}

}
