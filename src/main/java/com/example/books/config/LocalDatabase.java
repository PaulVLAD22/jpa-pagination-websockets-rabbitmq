//package com.example.books.config;
//
//import com.example.books.model.Book;
//import com.example.books.repository.BookRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//
//@Slf4j
//public class LocalDatabase {
//    @Bean
//    CommandLineRunner initDatabase(BookRepository repository) {
//
//        return args -> {
//            log.info("Preloading " + repository.save(new Book(1, "Bilbo Baggins", "burglar")));
//            log.info("Preloading " + repository.save(new Book(2, "Frodo Baggins", "thief")));
//        };
//    }
//}
