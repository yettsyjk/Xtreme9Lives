package com.skilldistillery.xtreme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilldistillery.xtreme.entities.Post;
import com.skilldistillery.xtreme.repositories.PostRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class PostTest {
	
	@Autowired
	private PostRepository postRepo;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	@DisplayName("repo mapping find post by id")
	void test1() {
			Post post = new Post();
			post.setId(1);
			assertNotNull(post);
			assertEquals("Xman@ier.com", postRepo.findById(1).get().getEmail());
			assertEquals("Xander Cage", postRepo.findById(1).get().getName());

	}

}
