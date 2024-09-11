package com.omer.sakila.movimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.entity.Purchase;
import com.omer.sakila.movimo.service.CustomerService;
import com.omer.sakila.movimo.service.EmailService;
import com.omer.sakila.movimo.service.FilmService;
import com.omer.sakila.movimo.service.PaymentService;
import com.omer.sakila.movimo.service.PurchaseService;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CustomerService customerService;
	
	public PurchaseController(PurchaseService purchaseService, PaymentService paymentService, EmailService emailService, FilmService filmService, CustomerService customerService) {
		this.purchaseService = purchaseService;
		this.paymentService = paymentService;
		this.emailService = emailService;
		this.filmService = filmService;
		this.customerService = customerService;
	}
	
	@PostMapping("/buy")
	public ResponseEntity<String> buyFilm(@RequestParam int customerId, @RequestParam int filmId, @RequestParam double amount) throws Exception {
		Purchase purchase = purchaseService.createPurchase(customerId, filmId);
		paymentService.createPayment(purchase.getId(), amount);
		
		Customer customer = customerService.findById(customerId);
		Film film = filmService.getFilmById(filmId);
		
		byte[] pdfInvoice = emailService.createInvoicePdf(customer, film, amount);
		
		emailService.sendPurchaseNotification(customer, film.getTitle(), pdfInvoice);
		
 		return ResponseEntity.ok("Film purchased successfully!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
