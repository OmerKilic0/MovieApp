package com.omer.sakila.movimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findByNameContainingIgnoreCase(String name);
	Category findById(int id);
	Category findByName(String name);
}
