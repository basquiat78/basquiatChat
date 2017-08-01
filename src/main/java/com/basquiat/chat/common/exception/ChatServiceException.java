package com.basquiat.chat.common.exception;

import org.springframework.dao.DataAccessException;

/**
 *
 * Create By Basquiat
 *
 */
public class ChatServiceException extends DataAccessException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg
	 */
	public ChatServiceException(String msg){
		super(msg);
	}

	/**
	 * @param msg
	 * @param cause
	 */
	public ChatServiceException(String msg, Throwable cause){
		super(msg, cause);
	}

}