package com.skilldistillery.xtreme.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.xtreme.entities.Comment;
import com.skilldistillery.xtreme.services.CommentService;

@RestController
@RequestMapping("api")
public class CommentController {
	
	@Autowired
	private CommentService commentSvc;
	
	@GetMapping("posts/{id}/comments")//http://localhost:8082/api/posts/1/comments
	public List<Comment> indexComments(@PathVariable int id,
			HttpServletRequest request,
			HttpServletResponse response){
		List<Comment> comments = commentSvc.findCommentsForPostById(id);
		if(comments == null) {
			response.setStatus(500);
			comments = null;
		}
		
		response.setStatus(200);

		return comments;
	}
	
	@PostMapping("posts/{id}/comments")//http://localhost:8082/api/posts/1/comments
	public Comment createComment(@PathVariable int id,
			@RequestBody Comment createComment,//json representation of Comment createComment
			HttpServletResponse response, HttpServletRequest request
			) {
		Comment createdComment = commentSvc.createNewCommentForPostById(id, createComment);
		if(createdComment == null ) {
			response.setStatus(404);
			return null;
		}else {
		response.setStatus(201);
		StringBuffer url = request.getRequestURL();
		url.append("/").append(createdComment.getId());
		response.setHeader("Location", url.toString());
		}
		return createdComment;//SELECT * FROM comment WHERE post_id = 1;
	}
	
	
	@DeleteMapping("posts/{id}/comments/{commentId}")//http://localhost:8082/api/posts/1/comments/5
	public void deleteComment(@PathVariable int id,
			@PathVariable int commentId, 
			HttpServletResponse response) {
		try {
			if(commentSvc.deleteCommentById(id, commentId)) {
				response.setStatus(204);//success deletion no content
				
			} else {
				response.setStatus(404);
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus(409);
		}
		
	}
	
	
	

}
