package com.skilldistillery.xtreme.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@JsonIgnore//avoid recursion from category to Post in collection OneToMany side
	@OneToMany(mappedBy="category", cascade= CascadeType.MERGE)
	private List<Post> post;
	
////////////////////////////////////
	
	public Category(int id, String name, List<Post> post) {
		super();
		this.id = id;
		this.name = name;
		this.post = post;
	}

	public Category() {
		super();
	}
	
	////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	
	
	
	
	

}
