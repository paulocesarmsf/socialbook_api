package com.socialbooksproject.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.socialbooksproject.domain.DetalhesErro;
import com.socialbooksproject.services.exceptions.AutorExistenteException;
import com.socialbooksproject.services.exceptions.AutorNaoEcontradoException;
import com.socialbooksproject.services.exceptions.LivroNaoEcontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEcontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(
			LivroNaoEcontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Livro não foi encontrado");
		erro.setMsgDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException(
			AutorExistenteException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("O autor já existe.");
		erro.setMsgDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEcontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEcontradoException(
			AutorNaoEcontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Autor não foi encontrado");
		erro.setMsgDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(
			DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida");
		erro.setMsgDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}
