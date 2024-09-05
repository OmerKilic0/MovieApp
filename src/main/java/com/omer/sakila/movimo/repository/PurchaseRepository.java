package com.omer.sakila.movimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
	Purchase findById(int id);
}
