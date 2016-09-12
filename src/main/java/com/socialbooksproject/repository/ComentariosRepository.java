package com.socialbooksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooksproject.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>{
}
