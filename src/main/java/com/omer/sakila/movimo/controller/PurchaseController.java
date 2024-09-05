package com.omer.sakila.movimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omer.sakila.movimo.entity.Purchase;
import com.omer.sakila.movimo.service.PaymentService;
import com.omer.sakila.movimo.service.PurchaseService;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PaymentService paymentService;
	
	public PurchaseController(PurchaseService purchaseService, PaymentService paymentService) {
		this.purchaseService = purchaseService;
		this.paymentService = paymentService;
	}
	
	@PostMapping("/buy")
	public ResponseEntity<String> buyFilm(@RequestParam int customerId, @RequestParam int filmId, @RequestParam double amount){
		Purchase purchase = purchaseService.createPurchase(customerId, filmId);
		paymentService.createPayment(purchase.getId(), amount);
		
		return ResponseEntity.ok("Film purchased successfully!");
	}
}
