package com.omer.sakila.movimo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{
	List<Rating> findById(int id);
	List<Rating> findByFilm(Film film);
	Optional<Rating> findByFilmAndCustomer(Film film, Customer customer);
	
	@Query("SELECT AVG(r.rating) FROM Rating r WHERE r.film = :film")
    Optional<Double> findAverageRatingByFilm(@Param("film") Film film);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.film = :film")
    int findTotalRatingsByFilm(@Param("film") Film film);
}
