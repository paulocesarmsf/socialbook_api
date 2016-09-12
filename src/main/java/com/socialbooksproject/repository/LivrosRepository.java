package com.socialbooksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooksproject.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
