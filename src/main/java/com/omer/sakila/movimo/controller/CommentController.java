package com.omer.sakila.movimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.service.CommentService;
import com.omer.sakila.movimo.service.CustomerService;
import com.omer.sakila.movimo.service.FilmService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CustomerService customerService;
	
	public CommentController(CommentService commentService, FilmService filmService, CustomerService customerService) {
		this.commentService = commentService;
		this.filmService = filmService;
		this.customerService = customerService;
	}
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam int filmId, @RequestParam String content) {
		Customer customer = customerService.authenticateUser();
		Film film = filmService.getFilmById(filmId);
		
		commentService.addComment(customer.getId(), filmId, content);
		
		return "redirect:/films/" + film.getTitle();
	}
	
	@PostMapping("/toggleCommentLike")
	public void updateLikeCount(@RequestParam int commentId) {
		commentService.toggleLike(commentId);
	}
	
	@PostMapping("/toggleCommentDislike")
	public void updateDislikeCount(@RequestParam int commentId) {
		commentService.toggleDislike(commentId);
	}	
}