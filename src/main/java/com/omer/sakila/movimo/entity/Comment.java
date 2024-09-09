package com.omer.sakila.movimo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="like_count")
	private int likeCount;
	
	@Column(name="dislike_count")
	private int dislikeCount;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "film_id", nullable = false)
	private Film film;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Column(name = "created_at", nullable = false)
    private Date createdAt;
	
	private boolean userLiked = false;
	private boolean userDisliked = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getDislikeCount() {
		return dislikeCount;
	}
	
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isUserLiked() {
		return userLiked;
	}

	public void setUserLiked(boolean userLiked) {
		this.userLiked = userLiked;
	}

	public boolean isUserDisliked() {
		return userDisliked;
	}

	public void setUserDisliked(boolean userDisliked) {
		this.userDisliked = userDisliked;
	}
}
