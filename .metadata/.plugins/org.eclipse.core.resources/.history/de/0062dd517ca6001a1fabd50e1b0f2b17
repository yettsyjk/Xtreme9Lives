package com.skilldistillery.xtreme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.xtreme.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByCategoryId(int id);
	
	List<Post> findByPriceBetween(double low, double high);//left to right
	
	List<Post> findByTitleContainingOrNameContaining(String name, String title);

	
	List <Post> findByNameLikeIgnoreCase(String keyword1,String keyword2);
}
