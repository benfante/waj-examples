package com.benfante.waj.pagilashopmonolith.repository;

import com.benfante.waj.pagilashopmonolith.model.Film;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {

}