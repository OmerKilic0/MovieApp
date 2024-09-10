package com.omer.sakila.movimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Comment;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.service.CommentService;
import com.omer.sakila.movimo.service.CustomerService;
import com.omer.sakila.movimo.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CommentService commentService;
	
	public ReplyController(ReplyService replyService, CustomerService customerService, CommentService commentService) {
		this.replyService = replyService;
		this.customerService = customerService;
		this.commentService = commentService;
	}
	
	@PostMapping("/addReply")
	public String addReply(@RequestParam int commentId, @RequestParam String content) {
		Customer customer = customerService.authenticateUser();
		Comment comment = commentService.findCommentById(commentId);
		
		replyService.addReply(commentId, customer.getId(), content);
		
		return "redirect:/films/" + comment.getFilm().getTitle();
	}
	
	@PostMapping("/toggleReplyLike")
    public void likeReply(@RequestParam int replyId) {
		Customer customer = customerService.authenticateUser();
        customerService.likeReply(customer.getId(), replyId);
    }

    @PostMapping("/toggleReplyDislike")
    public void dislikeReply(@RequestParam int replyId) {
    	Customer customer = customerService.authenticateUser();
    	customerService.dislikeReply(customer.getId(), replyId);
    }
}
