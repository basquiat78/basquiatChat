package com.basquiat.chat.common.exception;

/**
 *
 * Create By Basquiat
 *
 */
public class ChatRunTimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public ChatRunTimeException(String msg){
		super(msg);
	}

	/**
	 * @param cause
	 */
	public ChatRunTimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public ChatRunTimeException(String msg, Throwable cause){
		super(msg, cause);
	}

}