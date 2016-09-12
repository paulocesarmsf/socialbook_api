package com.socialbooksproject.services.exceptions;

public class LivroNaoEcontradoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LivroNaoEcontradoException(String msg) {
		super(msg);
	}
	public LivroNaoEcontradoException(String msg, Throwable causa) {
		super(msg, causa);
	} 
}
