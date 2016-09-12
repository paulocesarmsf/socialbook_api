package com.socialbooksproject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.socialbooksproject.domain.Comentario;
import com.socialbooksproject.domain.Livro;
import com.socialbooksproject.repository.ComentariosRepository;
import com.socialbooksproject.repository.LivrosRepository;
import com.socialbooksproject.services.exceptions.LivroNaoEcontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Livro> listar() {
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		Livro livro = livrosRepository.findOne(id);
		if(livro == null) {
			throw new LivroNaoEcontradoException("O Livro não pode ser encontrado");
		}
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.delete(id);
		} catch(EmptyResultDataAccessException e) {
			throw new LivroNaoEcontradoException("O Livro não pode ser encontrado");
		}
	}
	
	public void atualizar(Livro livro) {
		buscar(livro.getId());
		livrosRepository.save(livro);
	}

	public Comentario salvarComentario(Long idLivro, Comentario comentario) {
		Livro livro = buscar(idLivro);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentariosRepository.save(comentario);
	}
	public List<Comentario> listarComentarios(Long livroId) {
		Livro livro = buscar(livroId);
		
		return livro.getComentarios();
	}
	
}
