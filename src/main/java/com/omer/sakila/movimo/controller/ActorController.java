package com.omer.sakila.movimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Actor;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.service.ActorService;
import com.omer.sakila.movimo.service.CustomerService;
import com.omer.sakila.movimo.service.FilmService;

@Controller
@RequestMapping("/actors")
public class ActorController {

	private ActorService actorService;
	private FilmService filmService;
	private CustomerService customerService;

	public ActorController(ActorService actorService, FilmService filmService, CustomerService customerService) {
		this.actorService = actorService;
		this.filmService = filmService;
		this.customerService = customerService;
	}

	@GetMapping
	public String listActors(ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		model.addAttribute("actors", actorService.getAllActors());
		return "actors";
	}

	@GetMapping("/search")
	public String searchActors(@RequestParam(required = false) String name, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		if (name != null && !name.isEmpty()) {
			model.addAttribute("actors", actorService.searchActorsByName(name));
		} else {
			model.addAttribute("actors", actorService.getAllActors());
		}
		return "actors";
	}

	@GetMapping("/{id}")
	public String getActorById(@PathVariable Integer id, ModelMap model) {
		Actor actor = actorService.getActorById(id);
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		model.addAttribute("actor", actor);
		model.addAttribute("topSoldFilms", filmService.getTopSoldFilms(5));
		return "actor-details";
	}
}
