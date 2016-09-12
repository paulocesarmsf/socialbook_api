package com.socialbooksproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.socialbooksproject.domain.Autor;
import com.socialbooksproject.repository.AutoresRepository;
import com.socialbooksproject.services.exceptions.AutorExistenteException;
import com.socialbooksproject.services.exceptions.AutorNaoEcontradoException;

@Service
public class AutoresServices {

	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar() {
		return this.autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor a = autoresRepository.findOne(autor.getId());
			if(a != null) {
				throw new AutorExistenteException("O Autor já existe.");
			}
		}
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.findOne(id);
		if(autor == null) {
			throw new AutorNaoEcontradoException("O autor nao pode ser encontrado.");
		}
		return autor;
	}
	
	public void deletar(Long id) {
		try {
			autoresRepository.delete(id);
		} catch(EmptyResultDataAccessException e) {
			throw new AutorNaoEcontradoException("O autor não foi encontrado.");
		}
	}
	
	public void atualizar(Autor autor) {
		buscar(autor.getId());
		autoresRepository.save(autor);
	}
}
