package com.omer.sakila.movimo.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Comment;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.entity.Reply;
import com.omer.sakila.movimo.service.CommentService;
import com.omer.sakila.movimo.service.CustomerService;
import com.omer.sakila.movimo.service.FilmService;
import com.omer.sakila.movimo.service.PurchaseService;
import com.omer.sakila.movimo.service.RatingService;
import com.omer.sakila.movimo.service.ReplyService;

@Controller
@RequestMapping("/films")
public class FilmController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private RatingService ratingService;

	public FilmController(FilmService filmService, PurchaseService purchaseService, CustomerService customerService, CommentService commentService, ReplyService replyService, RatingService ratingService) {
		this.filmService = filmService;
		this.purchaseService = purchaseService;
		this.customerService = customerService;
		this.commentService = commentService;
		this.replyService = replyService;
		this.ratingService = ratingService;
	}

	@GetMapping
	public String listFilms(ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("films", filmService.getAllFilms());
		model.addAttribute("customer", customer);
		return "films";
	}

	@GetMapping("/search")
	public String searchFilms(@RequestParam(required = false) String name, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		if (name != null && !name.isEmpty()) {
			model.addAttribute("films", filmService.searchFilmsByName(name));
		} else {
			model.addAttribute("films", filmService.getAllFilms());
		}
		return "films";
	}
	
	@PostMapping("/rate")
	public String rateFilm(@RequestParam("filmId") int filmId, @RequestParam("rating") int rating, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute(customer);
		ratingService.rateFilm(filmId, customer.getId(), rating);
		return "redirect:/films/" + filmService.getFilmById(filmId).getTitle();
	}

	@GetMapping("/{title}")
	public String getFilmDetailsByTitle(@PathVariable String title, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		Film film = filmService.getFilmByTitle(title);

		Set<Integer> watchedFilmIds = customerService.getWatchedFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());

		Set<Integer> watchlistFilmIds = customerService.getWatchlistFilms(customer.getId()).stream().map(Film::getId)
				.collect(Collectors.toSet());

		List<Comment> comments = commentService.findCommentsByFilm(film.getId());
		List<Reply> replies = null;
		
		for (Comment comment : comments) {
	        boolean userLiked = customerService.hasLikedComment(customer.getId(), comment.getId());
	        boolean userDisliked = customerService.hasDislikedComment(customer.getId(), comment.getId());
	        
	        comment.setUserLiked(userLiked);
	        comment.setUserDisliked(userDisliked);
	        
	        replies = replyService.getRepliesByCommentId(comment.getId());
	        
	        for(Reply reply : replies) {
	        	boolean userLikedReply = customerService.hasLikedReply(customer.getId(), reply.getId());
	        	boolean userDislikedReply = customerService.hasDislikedReply(customer.getId(), reply.getId());
	        	
	        	reply.setUserLiked(userLikedReply);
	            reply.setUserDisliked(userDislikedReply);
	        }
	        comment.setReplies(replies);
	    }
		
		Double averageRating = ratingService.getAverageRating(film.getId());
        Integer totalRatings = ratingService.getTotalRatings(film.getId());
        int userRating = ratingService.getUserRatingForFilm(film.getId(), customer.getId());
		
		boolean hasPurchased = purchaseService.hasPurchasedFilm(customer.getId(), film.getId());
		if (film != null) {
			model.addAttribute("film", film);
			model.addAttribute("hasPurchased", hasPurchased);
			model.addAttribute("topSoldFilms", filmService.getTopSoldFilms(5));
			model.addAttribute("customer", customer);
			model.addAttribute("watched", watchedFilmIds);
			model.addAttribute("inWatchlist", watchlistFilmIds);
			model.addAttribute("comments", comments);
			model.addAttribute("replies", replies);
			model.addAttribute("averageRating", averageRating != null ? averageRating : 0.0);
	        model.addAttribute("totalRatings", totalRatings != null ? totalRatings : 0);
	        model.addAttribute("userRating", userRating);
		}
		return "film-details";
	}

	@PostMapping("/addToWatched")
	public String addToWatched(@RequestParam int filmId) {
		Customer customer = customerService.authenticateUser();
		customerService.addFilmToWatched(customer.getId(), filmId);
		return "redirect:/films/" + filmService.getFilmById(filmId).getTitle();
	}

	@PostMapping("/addToWatchlist")
	public String addToWatchlist(@RequestParam int filmId) {
		Customer customer = customerService.authenticateUser();
		customerService.addFilmToWatchList(customer.getId(), filmId);
		return "redirect:/films/" + filmService.getFilmById(filmId).getTitle();
	}

	@PostMapping("/removeFromWatched")
	public String removeFromWatched(@RequestParam int filmId) {
		Customer customer = customerService.authenticateUser();
		customerService.removeFilmFromWatched(customer.getId(), filmId);
		return "redirect:/films/" + filmService.getFilmById(filmId).getTitle();
	}

	@PostMapping("/removeFromWatchlist")
	public String removeFromWatchlist(@RequestParam int filmId) {
		Customer customer = customerService.authenticateUser();
		customerService.removeFilmFromWatchList(customer.getId(), filmId);
		return "redirect:/films/" + filmService.getFilmById(filmId).getTitle();
	}
}