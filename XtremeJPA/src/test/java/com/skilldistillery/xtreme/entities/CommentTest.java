package com.skilldistillery.xtreme.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("XtremePU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	@DisplayName("comment mapping")
	void test() {
		assertNotNull(comment);
		assertEquals("Augustus Gibbons", comment.getName());
		assertEquals("This seems dangerous", comment.getContent());
		assertNotNull(comment.getPost());
		assertEquals("Xander Cage", comment.getPost().getName());
	}

	
	
}
//SELECT * FROM comment WHERE id =1;
//+----+------------------+----------------------+---------+
//| id | name             | content              | post_id |
//+----+------------------+----------------------+---------+
//|  1 | Augustus Gibbons | This seems dangerous |       1 |
//+----+------------------+----------------------+---------+