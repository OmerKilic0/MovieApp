package com.omer.sakila.movimo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.omer.sakila.movimo.entity.Actor;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.service.ActorService;
import com.omer.sakila.movimo.service.FilmService;

@Controller
public class MenuController {

	@Autowired
    private FilmService filmService;

    @Autowired
    private ActorService actorService;
    
    public MenuController(FilmService filmService, ActorService actorService) {
    	this.filmService = filmService;
    	this.actorService = actorService;
    }
    
	@GetMapping("/")
	public String showMenu(ModelMap model) {
		List<Film> films = filmService.getAllFilms();
        List<Actor> actors = actorService.getAllActors();

        model.addAttribute("films", films);
        model.addAttribute("actors", actors);

		return "menu";
	}
}
