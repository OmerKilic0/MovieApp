package com.omer.sakila.movimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	Comment findById(int id);
	List<Comment> findByFilmId(int filmId);
}
