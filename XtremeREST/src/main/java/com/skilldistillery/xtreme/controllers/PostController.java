package com.skilldistillery.xtreme.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.xtreme.data.PostDAO;
import com.skilldistillery.xtreme.entities.Post;
import com.skilldistillery.xtreme.services.PostService;

@RestController
@RequestMapping("api")
public class PostController {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private PostService postSvc;

	@GetMapping("ping")
	public String ping(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(200);
		response.setHeader("Location", "http://localhost:8082/ping");

		return "pong";
	}

	/////// index/////////////http://localhost:8082/api/posts
	@GetMapping("posts")
	public List<Post> index() { // Post object representations will be returned
		return postDAO.index();
	}

	//////////// POST BY ID/////////http://localhost:8082/api/posts/1
	@GetMapping("posts/{id}")
	public Post getPostById(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
		Post post = postDAO.getPostById(id);
		if (post == null) {
			response.setStatus(404);
			System.out.println("invalid request");
		}

		return post;
	}

	//////////// CREATE POSTS/////////http://localhost:8082/api/posts/1
	@PostMapping("posts")
	public Post createPost(@RequestBody Post post, HttpServletResponse response, HttpServletRequest request) {
		Post createdPosts = postDAO.createPost(post);
		if (post != null) {
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(post.getId());
			response.setHeader("Location", url.toString());

			return createdPosts;
		} else {
			response.setStatus(500);
			createdPosts = null;

		}
		return post;
	}

	///////////// UPDATE POSTS///////////////http://localhost:8082/api/posts/91
	@PutMapping("posts/{id}")//requires something after the / or put wont work
	public Post updatePost(@PathVariable Integer id, @RequestBody Post post, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			post = postDAO.updatePost(post, id);
			if (post == null) {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return post;
	}

	//////////// DELETE POSTS////////////////
	@DeleteMapping("posts/{id}")
	public void deletePost(@PathVariable Integer id,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if(postDAO.deletePost(id)) {
				response.setStatus(204);
			}else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			response.setStatus(400);//foreign key constraint
		}
	}
	////////////SEARCH FOR CATEGORY///////////////
	@GetMapping("categories/{id}/posts")
	public List<Post> findByCategoryId(@PathVariable int id,
			HttpServletResponse response){
		List <Post> posts = postSvc.findByCategoryId(id);
		if(posts == null) {
			response.setStatus(404);
		}
		return posts;
	}

	///////////////SEARCH FOR POSTS//////////
	
	@GetMapping("posts/search/{keyword}")//http://localhost:8082/api/posts/search/xander
	public List<Post> postsByKeyword(@PathVariable String keyword){
		return postSvc.postsByKeyword(keyword);
	}
	
	/////////////POSTS BY PRICE RANGE//////////
	@GetMapping("posts/search/price/{low}/{high}")//http://localhost:8082/api/posts/search/price/12.99/6500.00
	public List<Post> findByPriceBetween(@PathVariable Double low,@PathVariable double high){
		return postSvc.findByPriceBetween(low, high);
	}
}
