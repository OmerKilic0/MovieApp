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
@Table(name = "reply")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "like_count")
	private int likeCount;

	@Column(name = "dislike_count")
	private int dislikeCount;

	@ManyToOne
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(name = "content", nullable = false, length = 1000)
	private String content;

	@Column(name = "created_at")
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
