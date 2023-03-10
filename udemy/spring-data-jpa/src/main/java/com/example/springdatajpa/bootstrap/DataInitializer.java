package com.example.springdatajpa.bootstrap;

import com.example.springdatajpa.entity.Book;
import com.example.springdatajpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Book book1 = new Book("DDD", "123", "Uday");
        Book book2 = new Book("Java", "1234", "Uday");

        Book savedBook1 = bookRepository.save(book1);
        Book savedBook2 = bookRepository.save(book2);

        System.out.println(savedBook1.toString());
        System.out.println(savedBook2);

        var bookList = bookRepository.findAll();
        for (Book book : bookList) {
            System.out.print(book + " || ");
        }

    }
}
