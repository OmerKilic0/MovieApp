package com.omer.sakila.movimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.service.FilmService;
import com.omer.sakila.movimo.service.PurchaseService;

@Controller
@RequestMapping("/films")
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	public FilmController(FilmService filmService, PurchaseService purchaseService) {
		this.filmService = filmService;
		this.purchaseService = purchaseService;
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
        boolean hasPurchased = purchaseService.hasPurchasedFilm(1, film.getId());
        if (film != null) {
            model.addAttribute("film", film);
            model.addAttribute("hasPurchased", hasPurchased);
        }
        return "film-details";
    }
}