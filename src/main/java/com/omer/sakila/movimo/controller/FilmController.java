package com.omer.sakila.movimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.service.FilmService;

@Controller
@RequestMapping("/films")
public class FilmController {

	private FilmService filmService;
	
	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}
	
	@GetMapping
	public String listFilms(ModelMap model) {
		model.addAttribute("films", filmService.getAllFilms());
		return "films";
	}
	
	@GetMapping("/search")
	public String searchFilms(@RequestParam(required=false) String name, ModelMap model) {
		 if (name != null && !name.isEmpty()) {
	            model.addAttribute("films", filmService.searchFilmsByName(name));
	        } else {
	            model.addAttribute("films", filmService.getAllFilms());
	        }
	        return "films";
	}
	
	@GetMapping("/{title}")
    public String getFilmDetailsByTitle(@PathVariable String title, ModelMap model) {
        Film film = filmService.getFilmByTitle(title);
        if (film != null) {
            model.addAttribute("film", film);
        }
        return "film-details";
    }
}