package com.omer.sakila.movimo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Comment;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.entity.Reply;
import com.omer.sakila.movimo.repository.CommentRepository;
import com.omer.sakila.movimo.repository.CustomerRepository;
import com.omer.sakila.movimo.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public ReplyService(ReplyRepository replyRepository, CommentRepository commentRepository, CustomerRepository customerRepository) {
		this.replyRepository = replyRepository;
		this.commentRepository = commentRepository;
		this.customerRepository = customerRepository;
	}
	
	public List<Reply> getRepliesByCommentId(int commentId){
		return replyRepository.findByCommentId(commentId);
	}
	
	public Reply addReply(int commentId, int customerId, String content) {
		Reply reply = new Reply();
		
		Comment comment = commentRepository.findById(commentId);
		Customer customer = customerRepository.findById(customerId);
		
		reply.setComment(comment);
		reply.setCustomer(customer);
		reply.setContent(content);
		reply.setCreatedAt(new Date());
		
		return replyRepository.save(reply);
	}
	
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
