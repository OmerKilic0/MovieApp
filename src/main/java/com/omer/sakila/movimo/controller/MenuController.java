package com.omer.sakila.movimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/")
	public String showMenu() {
		return "menu";
	}
	
//	@GetMapping("/search")
//	public String searchFilms(@RequestParam(required=false) String title, ModelMap model) {
//		 if (title != null && !title.isEmpty()) {
//	            model.addAttribute("films", filmService.searchFilmsByName(title));
//	        } else {
//	            model.addAttribute("films", filmService.getAllFilms());
//	        }
//	        return "films";
//	}
}
