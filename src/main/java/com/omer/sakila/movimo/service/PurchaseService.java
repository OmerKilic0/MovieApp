package com.omer.sakila.movimo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.entity.Purchase;
import com.omer.sakila.movimo.repository.CustomerRepository;
import com.omer.sakila.movimo.repository.FilmRepository;
import com.omer.sakila.movimo.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public PurchaseService(PurchaseRepository purchaseRepository, FilmRepository filmRepository, CustomerRepository customerRepository, JdbcTemplate jdbcTemplate) {
		this.purchaseRepository = purchaseRepository;
		this.filmRepository = filmRepository;
		this.customerRepository = customerRepository;
		this.jdbcTemplate = jdbcTemplate;
	}

    public boolean hasPurchasedFilm(int customerId, int filmId) {
        String sql = "SELECT COUNT(*) > 0 AS has_purchased FROM purchase WHERE customer_id = ? AND film_id = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, customerId, filmId);
    }
	
	public Purchase createPurchase(int customerId, int filmId) {
		Customer customer = customerRepository.findById(customerId);
		Film film = filmRepository.findById(filmId);
		
		Purchase purchase = new Purchase();
		purchase.setCustomer(customer);
		purchase.setFilm(film);
		purchase.setPurchaseDate(new Date());
		purchase.setLastUpdate(new Date());
		purchaseRepository.save(purchase);
		
		return purchase;
	}
	
}
