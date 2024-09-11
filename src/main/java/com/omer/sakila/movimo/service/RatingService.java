package com.omer.sakila.movimo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.entity.Rating;
import com.omer.sakila.movimo.repository.CustomerRepository;
import com.omer.sakila.movimo.repository.FilmRepository;
import com.omer.sakila.movimo.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
    private CustomerRepository customerRepository;
	
	public RatingService(RatingRepository ratingRepository, FilmRepository filmRepository, CustomerRepository customerRepository) {
		this.ratingRepository = ratingRepository;
		this.filmRepository = filmRepository;
		this.customerRepository = customerRepository;
	}
	
	public void rateFilm(int filmId, int customerId, int ratingValue) {
		Film film = filmRepository.findById(filmId);
		Customer customer = customerRepository.findById(customerId);
		
		Optional<Rating> existingRating = ratingRepository.findByFilmAndCustomer(film, customer);
		if(existingRating.isPresent()) {
			Rating rating = existingRating.get();
			rating.setRating(ratingValue);
			ratingRepository.save(rating);
		}
		else {
			Rating newRating = new Rating(film, customer, ratingValue);
			ratingRepository.save(newRating);
		}
	}
		
	public Double getAverageRating(int filmId) {
		Film film = filmRepository.findById(filmId);
		return ratingRepository.findAverageRatingByFilm(film).orElse(null);
	}
		
	public int getTotalRatings(int filmId) {
		Film film = filmRepository.findById(filmId);
		return ratingRepository.findTotalRatingsByFilm(film);
	}
	
	public int getUserRatingForFilm(int filmId, int customerId) {
		Film film = filmRepository.findById(filmId);
		Customer customer = customerRepository.findById(customerId);
		Optional<Rating> existingRating = ratingRepository.findByFilmAndCustomer(film, customer);
		if(existingRating.isPresent()) {
			return existingRating.get().getRating();
		}
		return 0;
	}
}
