package com.example.springdatajpa;

import com.example.springdatajpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringDataJpaApplicationTests {

	private final BookRepository bookRepository;

	@Autowired
	public SpringDataJpaApplicationTests(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testBookRepo() {
		long count = bookRepository.count();
		assertThat(count).isGreaterThan(0);
	}

}
