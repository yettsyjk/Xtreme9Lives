package com.skilldistillery.xtreme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilldistillery.xtreme.entities.Category;
import com.skilldistillery.xtreme.repositories.CategoryRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryTest {
	
	@Autowired
	private CategoryRepository catRepo;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	@DisplayName("repo mapping find category by id")
	void test1() {
//			Category cat = new Category();
//			cat.setId(1);
		Optional<Category> cat = catRepo.findById(1);
		assertTrue(cat.isPresent());
			assertNotNull(cat);
			
			assertEquals("skydiving", catRepo.findById(1).get().getName());
//SELECT * FROM category;
	}

}
//SELECT * FROM category ORDER BY id;
//+----+----------------+
//| id | name           |
//+----+----------------+
//|  1 | skydiving      |
//|  2 | mountaineering |
//|  3 | heli-skiing    |
//|  4 | diving         |
//|  5 | biking         |
//+----+----------------+