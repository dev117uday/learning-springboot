package com.example.springdatajpa;

import com.example.springdatajpa.entity.Book;
import com.example.springdatajpa.repository.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// data jpa test brings minimal context for testing, only JPA layer is loaded
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = { "com.example.springdatajpa.bootstrap" })
// component scan to init bean, specifying in base package exactly to only initialize that
public class SpringJPATestSlice {

    @Autowired
    BookRepository bookRepository;

    @Order(1)
    @Test
    @Rollback(value = false)
    @Commit
    // to keep data for next test cases
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();
        bookRepository.save(new Book("My book","12345","Me"));
        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }
}
