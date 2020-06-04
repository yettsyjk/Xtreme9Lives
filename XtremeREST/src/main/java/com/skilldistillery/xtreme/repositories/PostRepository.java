package com.skilldistillery.xtreme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.xtreme.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByCategoryId(int id);
	
	List<Post> findByPriceBetween(double low, double high);//left to right
	
	List<Post> findByTitleContainingOrNameContaining(String name, String title);
}
