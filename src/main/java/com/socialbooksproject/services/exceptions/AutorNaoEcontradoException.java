package com.socialbooksproject.services.exceptions;

public class AutorNaoEcontradoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutorNaoEcontradoException(String msg) {
		super(msg);
	}
	public AutorNaoEcontradoException(String msg, Throwable causa) {
		super(msg, causa);
	} 
}
