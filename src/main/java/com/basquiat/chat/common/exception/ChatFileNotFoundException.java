package com.basquiat.chat.common.exception;

/**
 *
 * Create By Basquiat
 *
 */
public class ChatFileNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public ChatFileNotFoundException(String msg){
		super(msg);
	}

	/**
	 * @param cause
	 */
	public ChatFileNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public ChatFileNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}