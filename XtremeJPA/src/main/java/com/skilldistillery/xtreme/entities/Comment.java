package com.skilldistillery.xtreme.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String content;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore//avoid recursive 
	@JoinColumn(name= "post_id")
	private Post post;
	
	
	
//////////////////////////////
	public Comment() {
		super();
	}

	public Comment(int id, String name, String content, Post post) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.post = post;
	}
	
	
	
//////////////////////////////



	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + "]";
	}
	
}
