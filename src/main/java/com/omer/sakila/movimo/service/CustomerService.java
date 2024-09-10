package com.omer.sakila.movimo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Comment;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Film;
import com.omer.sakila.movimo.entity.Reply;
import com.omer.sakila.movimo.repository.CommentRepository;
import com.omer.sakila.movimo.repository.CustomerRepository;
import com.omer.sakila.movimo.repository.FilmRepository;
import com.omer.sakila.movimo.repository.ReplyRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	public CustomerService(CustomerRepository customerRepository, FilmRepository filmRepository, CommentRepository commentRepository, ReplyRepository replyRepository) {
		this.customerRepository = customerRepository;
		this.filmRepository = filmRepository;
		this.commentRepository = commentRepository;
		this.replyRepository = replyRepository;
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer findById(int id) {
		return customerRepository.findById(id);
	}
	
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	public Customer authenticateUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String customerEmail = authentication.getName();
	    
	    Customer customer = findByEmail(customerEmail);
	    return customer;
	}
	
	public void addFilmToWatched(int customerId, int filmId) {
        Customer customer = customerRepository.findById(customerId);
        Film film = filmRepository.findById(filmId);
        customer.getWatchedFilms().add(film);
        customerRepository.save(customer);
    }

    public void addFilmToWatchList(int customerId, int filmId) {
        Customer customer = customerRepository.findById(customerId);
        Film film = filmRepository.findById(filmId);
        customer.getWatchlist().add(film);
        customerRepository.save(customer);
    }
    
    public void removeFilmFromWatched(int customerId, int filmId) {
    	Customer customer = customerRepository.findById(customerId);
    	Film film = filmRepository.findById(filmId);
    	customer.getWatchedFilms().remove(film);
    	customerRepository.save(customer);
    }
    
    public void removeFilmFromWatchList(int customerId, int filmId) {
    	Customer customer = customerRepository.findById(customerId);
    	Film film = filmRepository.findById(filmId);
    	customer.getWatchlist().remove(film);
    	customerRepository.save(customer);
    }
    
    public Set<Film> getWatchedFilms(int customerId) {
        return customerRepository.findById(customerId).getWatchedFilms();
    }

    public Set<Film> getWatchlistFilms(int customerId) {
        return customerRepository.findById(customerId).getWatchlist();
    }
    
    public void likeComment(int customerId, int commentId) {
        Customer customer = customerRepository.findById(customerId);
        Comment comment = commentRepository.findById(commentId);
        
        if (!hasLikedComment(customerId, commentId)) {
            customer.getLikedComments().add(comment);
            customer.getDislikedComments().remove(comment);
            comment.setLikeCount(comment.getLikeCount() + 1);
            if (hasDislikedComment(customerId, commentId)) {
                comment.setDislikeCount(comment.getDislikeCount() - 1);
            }
            commentRepository.save(comment);
            customerRepository.save(customer);
        }
        else {
        	customer.getLikedComments().remove(comment);
        	comment.setLikeCount(comment.getLikeCount() - 1);
            commentRepository.save(comment);
        }
    }
    
    public void dislikeComment(int customerId, int commentId) {
    	Customer customer = customerRepository.findById(customerId);
        Comment comment = commentRepository.findById(commentId);
        
        if(!hasDislikedComment(customerId, commentId)) {
        	customer.getDislikedComments().add(comment);
        	customer.getLikedComments().remove(comment);
        	comment.setDislikeCount(comment.getDislikeCount() + 1);
            if (hasLikedComment(customerId, commentId)) {
                comment.setLikeCount(comment.getLikeCount() - 1);
            }
        	customerRepository.save(customer);
        	commentRepository.save(comment);
        }
        else {
        	customer.getDislikedComments().remove(comment);
        	comment.setDislikeCount(comment.getDislikeCount() - 1);
        	commentRepository.save(comment);
        }
    }
    
    public void likeReply(int customerId, int replyId) {
    	Customer customer = customerRepository.findById(customerId);
    	Reply reply = replyRepository.findById(replyId);
    	
    	if(!hasLikedReply(customerId, replyId)) {
    		customer.getLikedReplies().add(reply);
    		customer.getDislikedReplies().remove(reply);
    		reply.setLikeCount(reply.getLikeCount() + 1);
    		if (hasDislikedReply(customerId, replyId)) {
                reply.setDislikeCount(reply.getDislikeCount() - 1);
            }
            replyRepository.save(reply);
            customerRepository.save(customer);
    	}
    	else {
    		customer.getLikedReplies().remove(reply);
    		reply.setLikeCount(reply.getLikeCount() - 1);
    		replyRepository.save(reply);
    	}
    }
    
    public void dislikeReply(int customerId, int replyId) {
    	Customer customer = customerRepository.findById(customerId);
        Reply reply = replyRepository.findById(replyId);
        
        if(!hasDislikedReply(customerId, replyId)) {
        	customer.getDislikedReplies().add(reply);
        	customer.getLikedReplies().remove(reply);
        	reply.setDislikeCount(reply.getDislikeCount() + 1);
            if (hasLikedReply(customerId, replyId)) {
                reply.setLikeCount(reply.getLikeCount() - 1);
            }
        	customerRepository.save(customer);
        	replyRepository.save(reply);
        }
        else {
        	customer.getDislikedReplies().remove(reply);
        	reply.setDislikeCount(reply.getDislikeCount() - 1);
        	replyRepository.save(reply);
        }
    }
    
    public boolean hasLikedComment(int customerId, int commentId) {
        return customerRepository.hasLikedComment(customerId, commentId);
    }

    public boolean hasDislikedComment(int customerId, int commentId) {
        return customerRepository.hasDislikedComment(customerId, commentId);
    }
    
    public boolean hasLikedReply(int customerId, int replyId) {
        return customerRepository.hasLikedReply(customerId, replyId);
    }

    public boolean hasDislikedReply(int customerId, int replyId) {
        return customerRepository.hasDislikedReply(customerId, replyId);
    }
}