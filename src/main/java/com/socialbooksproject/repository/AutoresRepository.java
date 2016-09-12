package com.socialbooksproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooksproject.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{
}
