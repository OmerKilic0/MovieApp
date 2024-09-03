package com.omer.sakila.movimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{
	List<Actor> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
	Actor findById(int id);
}