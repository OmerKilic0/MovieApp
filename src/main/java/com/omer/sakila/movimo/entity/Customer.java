package com.omer.sakila.movimo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "last_update")
	private Date lastUpdate;

	@ManyToMany
	@JoinTable(name = "customer_watched_films", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "film_id"))
	private Set<Film> watchedFilms = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "customer_watchlist", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "film_id"))
	private Set<Film> watchlist = new HashSet<>();
	
	@ManyToMany
    @JoinTable(
        name = "customer_liked_comment",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private Set<Comment> likedComments = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "customer_disliked_comment",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private Set<Comment> dislikedComments = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Film> getWatchedFilms() {
		return watchedFilms;
	}

	public void setWatchedFilms(Set<Film> watchedFilms) {
		this.watchedFilms = watchedFilms;
	}

	public Set<Film> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(Set<Film> watchlist) {
		this.watchlist = watchlist;
	}

	public Set<Comment> getLikedComments() {
		return likedComments;
	}

	public void setLikedComments(Set<Comment> likedComments) {
		this.likedComments = likedComments;
	}

	public Set<Comment> getDislikedComments() {
		return dislikedComments;
	}

	public void setDislikedComments(Set<Comment> dislikedComments) {
		this.dislikedComments = dislikedComments;
	}
}