package com.cg.oam.exception;

/**
 * The Class InvalidDataException.
 */
public class InvalidDataException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Instantiates a new invalid data exception.
	 */
	public InvalidDataException() {}
	
	/**
	 * Instantiates a new invalid data exception.
	 *
	 * @param message - the message
	 */
	public InvalidDataException(String message) {
		super(message);
	}

}
