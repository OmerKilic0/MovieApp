package com.omer.sakila.movimo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.repository.FilmRepository;

@Service
public class FilmService {

	private FilmRepository filmRepository;
	
	public FilmService(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}
	
	public List<Film> getAllFilms(){
		return filmRepository.findAll();
	}
	
	public List<Film> searchFilmsByName(String name){
		return filmRepository.findAll().stream()
                .filter(film -> film.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
	}
	
	public Film getFilmById(int id) {
        return filmRepository.findById(id);
    }
	
	public Film getFilmByTitle(String title) {
		return filmRepository.findByTitle(title);
	}
	
	public List<Film> getTopSoldFilms(int limit) {
        return filmRepository.findTopSoldFilms(PageRequest.of(0, limit));
    }
}
