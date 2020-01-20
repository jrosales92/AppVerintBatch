package com.bbva.verint.exception;

public class InvalidJSON004 extends CustomException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor del objeto.
	 */
	public InvalidJSON004() {
		super();
	}

	/**
	 * Metodo ArchivingException <br>
	 *
	 * @param cause Causa del error
	 */
	public InvalidJSON004(Throwable cause) {
		super( "Error el JSON recibido es invalido", cause);
		super.setCodigoError("004");
	}

}
