package com.basquiat.chat.common.exception;

/**
 *
 * Create By Basquiat
 *
 */
public class UnauthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public UnauthorizedException(String msg) {
		super(msg);
	}

	/**
	 * @param cause
	 */
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public UnauthorizedException(String msg, Throwable cause){
		super(msg, cause);
	}

}