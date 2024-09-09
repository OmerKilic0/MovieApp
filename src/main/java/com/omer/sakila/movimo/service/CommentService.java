package com.omer.sakila.movimo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Comment;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.repository.CommentRepository;
import com.omer.sakila.movimo.repository.CustomerRepository;
import com.omer.sakila.movimo.repository.FilmRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public CommentService(CommentRepository commentRepository, FilmRepository filmRepository,
			CustomerRepository customerRepository) {
		this.commentRepository = commentRepository;
		this.filmRepository = filmRepository;
		this.customerRepository = customerRepository;
	}

	public void addComment(int customerId, int filmId, String content) {
		Customer customer = customerRepository.findById(customerId);
		Film film = filmRepository.findById(filmId);

		Comment comment = new Comment();
		comment.setCustomer(customer);
		comment.setFilm(film);
		comment.setComment(content);
		comment.setCreatedAt(new Date());

		commentRepository.save(comment);
	}

	public void toggleLike(int commentId) {
		Comment comment = commentRepository.findById(commentId);

		if (comment.isUserLiked()) {
			comment.setLikeCount(comment.getLikeCount() - 1);
			comment.setUserLiked(false);
		} else {
			if (comment.isUserDisliked()) {
				comment.setDislikeCount(comment.getDislikeCount() - 1);
				comment.setUserDisliked(false);
			}
			comment.setLikeCount(comment.getLikeCount() + 1);
			comment.setUserLiked(true);
		}

		commentRepository.save(comment);
	}

	public void toggleDislike(int commentId) {
		Comment comment = commentRepository.findById(commentId);

		if (comment.isUserDisliked()) {
			comment.setDislikeCount(comment.getDislikeCount() - 1);
			comment.setUserDisliked(false);
		} else {
			if (comment.isUserLiked()) {
				comment.setLikeCount(comment.getLikeCount() - 1);
				comment.setUserLiked(false);
			}
			comment.setDislikeCount(comment.getDislikeCount() + 1);
			comment.setUserDisliked(true);
		}

		commentRepository.save(comment);
	}

	public List<Comment> findCommentsByFilm(int filmId) {
		return commentRepository.findByFilmId(filmId);
	}
	
	public Comment findCommentById(int commentId) {
		return commentRepository.findById(commentId);
	}
}
