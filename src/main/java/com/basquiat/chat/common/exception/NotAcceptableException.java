package com.basquiat.chat.common.exception;

/**
 *
 * Create By Basquiat
 *
 */
public class NotAcceptableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public NotAcceptableException(String msg) {
		super(msg);
	}

	/**
	 * @param cause
	 */
	public NotAcceptableException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public NotAcceptableException(String msg, Throwable cause){
		super(msg, cause);
	}

}