package com.socialbooksproject.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialbooksproject.domain.Autor;
import com.socialbooksproject.services.AutoresServices;

@RestController
@RequestMapping("/autores")
public class AutoresResource {

	@Autowired
	private AutoresServices autoresServices;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listar() {
		List<Autor> autores = this.autoresServices.listar();
		CacheControl cacheControl = CacheControl.maxAge(100, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(autores);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
		autor = autoresServices.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(autor.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscar(@PathVariable("id") Long id) {
		Autor autor = autoresServices.buscar(id);
		CacheControl cacheControl = CacheControl.maxAge(1000, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(autor);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		autoresServices.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Autor autor, @PathVariable("id") Long id) {
		autor.setId(id);
		autoresServices.atualizar(autor);
		return ResponseEntity.noContent().build();
	}
}
