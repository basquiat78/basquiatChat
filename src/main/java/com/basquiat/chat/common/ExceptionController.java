package com.basquiat.chat.common;

import com.basquiat.chat.common.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Create By Basquiat
 *
 * 모든 Controller는 일괄적인 에러 처리를 위해 해당 콘트롤러를 상속받는다.
 *
 */
public abstract class ExceptionController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	
	Map<String, String> error = new HashMap<>();
	
	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	Object handleException(IOException e) {
		LOG.error("IOException", e);
		error.put("msg", e.getMessage());
		return error;
	}
	
	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	Object handleException(DataAccessException e) {
		LOG.error("DataAccessException", e);
		error.put("msg", e.getMessage());
		return error;
	}
	
	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	Object handleException(Exception e) {
		LOG.error("Exception", e);
		error.put("msg", e.getMessage());
		return error;
	}

	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(ChatServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody
	Object ServiceException(ChatServiceException e){
		LOG.warn("CHAT - ServiceException", e);
		error.put("msg", e.getMessage());
		return error;
	}

	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(ChatRunTimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody
	Object CHATRunTimeException(ChatRunTimeException e){
		LOG.warn("CHAT - CHATRunTimeException : " + e.getMessage());
		error.put("msg", e.getMessage());
		return error;
	}

	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody
	Object AuthException(UnauthorizedException e){
		LOG.warn("CHAT - UnauthorizedException : "+ e.getMessage());
		error.put("msg", e.getMessage());
		return error;
	}

	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(ChatFileNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody
	Object handleException(ChatFileNotFoundException e) {
		LOG.warn("CHAT - CHATFileNotFoundException : " + e.getMessage());
		error.put("msg", e.getMessage());
		return error;
	}

	/**
	 * @param e
	 * @return Object
	 */
	@ExceptionHandler(NotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody
	Object handleException(NotAcceptableException e) {
		LOG.warn("CHAT - NotAcceptableException : " + e.getMessage());
		error.put("msg", e.getMessage());
		return error;
	}
	
}