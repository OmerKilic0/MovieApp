package com.omer.sakila.movimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findById(int id);
	Customer findByEmail(String email);
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
	           "FROM Customer cu JOIN cu.likedComments c WHERE cu.id = :customerId AND c.id = :commentId")
	    boolean hasLikedComment(@Param("customerId") int customerId, @Param("commentId") int commentId);

	    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
	           "FROM Customer cu JOIN cu.dislikedComments c WHERE cu.id = :customerId AND c.id = :commentId")
	    boolean hasDislikedComment(@Param("customerId") int customerId, @Param("commentId") int commentId);
}
