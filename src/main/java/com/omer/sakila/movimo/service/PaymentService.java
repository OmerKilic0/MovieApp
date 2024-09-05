package com.omer.sakila.movimo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Payment;
import com.omer.sakila.movimo.entity.Purchase;
import com.omer.sakila.movimo.repository.PaymentRepository;
import com.omer.sakila.movimo.repository.PurchaseRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	public PaymentService(PaymentRepository paymentRepository, PurchaseRepository purchaseRepository) {
		this.paymentRepository = paymentRepository;
		this.purchaseRepository = purchaseRepository;
	}
	
	public Payment createPayment(int purchaseId, double amount) {
		Purchase purchase = purchaseRepository.findById(purchaseId);
		
		Payment payment = new Payment();
		payment.setPurchase(purchase);
		payment.setCustomer(purchase.getCustomer());
		payment.setAmount(amount);
		payment.setPaymentDate(new Date());
		payment.setLastUpdate(new Date());
		paymentRepository.save(payment);
		
		return payment;
	}
	
}
