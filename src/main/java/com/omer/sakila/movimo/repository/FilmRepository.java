package com.omer.sakila.movimo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>{
	List<Film> findByTitleContainingIgnoreCase(String title);
	Film findById(int id);
	Film findByTitle(String title);
	
	@Query("SELECT f FROM Film f JOIN f.purchases p GROUP BY f.id ORDER BY COUNT(p.id) DESC")
	List<Film> findTopSoldFilms(Pageable pageable);
}
