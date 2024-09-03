package com.omer.sakila.movimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Actor;
import com.omer.sakila.movimo.service.ActorService;

@Controller
@RequestMapping("/actors")
public class ActorController {

	private ActorService actorService;
	
	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}
	
	@GetMapping
	public String listActors(ModelMap model) {
		model.addAttribute("actors", actorService.getAllActors());
		return "actors";
	}
	
	@GetMapping("/search")
	public String searchActors(@RequestParam(required=false) String name, ModelMap model) {
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
        model.addAttribute("actor", actor);
        return "actor-details";
    }
}
