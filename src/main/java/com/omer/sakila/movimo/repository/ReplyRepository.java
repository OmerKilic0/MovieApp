package com.omer.sakila.movimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omer.sakila.movimo.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	Reply findById(int id);
	List<Reply> findByCommentId(int commentId);
}
