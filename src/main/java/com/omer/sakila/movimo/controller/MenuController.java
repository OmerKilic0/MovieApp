package com.omer.sakila.movimo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Actor;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.service.ActorService;
import com.omer.sakila.movimo.service.CustomerService;
import com.omer.sakila.movimo.service.FilmService;

@Controller
public class MenuController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private CustomerService customerService;

	public MenuController(FilmService filmService, ActorService actorService, CustomerService customerService) {
		this.filmService = filmService;
		this.actorService = actorService;
		this.customerService = customerService;
	}

	@GetMapping("/")
	public String showMenu(ModelMap model) {
		List<Film> films = filmService.getAllFilms();
		List<Actor> actors = actorService.getAllActors();
		Customer customer = customerService.authenticateUser();

		Set<Integer> watchedFilmIds = customerService.getWatchedFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());

		Set<Integer> watchlistFilmIds = customerService.getWatchlistFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());

		model.addAttribute("films", films);
		model.addAttribute("actors", actors);
		model.addAttribute("customer", customer);
		model.addAttribute("watched", watchedFilmIds);
		model.addAttribute("inWatchlist", watchlistFilmIds);
		return "menu";
	}

	@GetMapping("watched-films")
	public String showWatchedFilms(ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute(customer);
		Set<Integer> watchedFilmIds = customerService.getWatchedFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());
		List<Film> watchedFilms = new ArrayList<>();

		for (int watchedFilmId : watchedFilmIds) {
			watchedFilms.add(filmService.getFilmById(watchedFilmId));
		}

		model.addAttribute("watchedFilms", watchedFilms);
		return "watched-films";
	}

	@GetMapping("watched-films/search")
	public String searchFilmsWatched(@RequestParam(required = false) String name, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		Set<Integer> watchedFilmIds = customerService.getWatchedFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());
		List<Film> watchedFilms = new ArrayList<>();

		for (int watchedFilmId : watchedFilmIds) {
			watchedFilms.add(filmService.getFilmById(watchedFilmId));
		}

		if (name != null && !name.isEmpty()) {
			model.addAttribute("watchedFilms", filmService.searchFilmsByName(name));
		} else {
			model.addAttribute("watchedFilms", watchedFilms);
		}
		return "watched-films";
	}
	
	@GetMapping("watchlist")
	public String showWatchist(ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute(customer);
		Set<Integer> watchlistFilmIds = customerService.getWatchlistFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());
		List<Film> watchlist = new ArrayList<>();

		for (int watchlistFilmId : watchlistFilmIds) {
			watchlist.add(filmService.getFilmById(watchlistFilmId));
		}

		model.addAttribute("watchlist", watchlist);
		return "watchlist";
	}

	@GetMapping("watchlist/search")
	public String searchFilmsWatchlist(@RequestParam(required = false) String name, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		Set<Integer> watchlistFilmIds = customerService.getWatchlistFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());
		List<Film> watchlist = new ArrayList<>();

		for (int watchlistFilmId : watchlistFilmIds) {
			watchlist.add(filmService.getFilmById(watchlistFilmId));
		}

		if (name != null && !name.isEmpty()) {
			model.addAttribute("watchlist", filmService.searchFilmsByName(name));
		} else {
			model.addAttribute("watchlist", watchlist);
		}
		return "watchlist";
	}
}
