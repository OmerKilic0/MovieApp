package com.omer.sakila.movimo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Actor;
import com.omer.sakila.movimo.repository.ActorRepository;

@Service
public class ActorService {

	private ActorRepository actorRepository;
	
	public ActorService(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}
	
	public List<Actor> getAllActors() {
		return actorRepository.findAll();
	}
	
	public List<Actor> searchActorsByName(String name){
		return actorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
	}
	
	public Actor getActorById(int id) {
		return actorRepository.findById(id);
	}
}
