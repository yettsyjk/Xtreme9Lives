package com.skilldistillery.xtreme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.xtreme.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByPostId(int id);
	//SELECT * FROM comment WHERE post_id = 1;
//	+----+------------------+----------------------+---------+
//	| id | name             | content              | post_id |
//	+----+------------------+----------------------+---------+
//	|  1 | Augustus Gibbons | This seems dangerous |       1 |
//	|  3 | John Simmons     | Can I jump solo?     |       1 |
//	+----+------------------+----------------------+---------+

}
