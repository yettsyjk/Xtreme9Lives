package com.skilldistillery.xtreme.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.xtreme.entities.Post;


@Service
@Transactional
public class PostDAOImpl implements PostDAO{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Post> index() {
		String jpql = "SELECT p FROM Post p";
		List<Post> posts = em.createQuery(jpql, Post.class).getResultList();
//		System.out.println("*************posts*************");
//		System.out.println(posts);
		return posts;
	}

	@Override
	public Post createPost(Post post) {
		try {
			em.persist(post);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return post;
	}

	@Override
	public Post getPostById(int id) {
		return em.find(Post.class, id);
	}

	@Override
	public Post updatePost(Post post, int id) {
		Post updatedPost = em.find(Post.class, id);//managed entity out
		if( updatedPost != null) {
			
			updatedPost.setTitle(post.getTitle());
			updatedPost.setName(post.getName());
			
			updatedPost.setDescription(post.getDescription());
			updatedPost.setPrice(post.getPrice());
			updatedPost.setImageUrl(post.getImageUrl());
			updatedPost.setBrand(post.getBrand());
			
			updatedPost.setEmail(post.getEmail());
			em.flush();//sink the changes back into database turned into a sql update statement and binding values
		}
		return updatedPost;
	}
////////////////////////////
	@Override
	public boolean deletePost(int id) {
		boolean deleted = false;
		Post post = em.find(Post.class, id);
		if(post != null) {
			try {
				em.remove(post);
				deleted = true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return deleted;
	}
	

}
