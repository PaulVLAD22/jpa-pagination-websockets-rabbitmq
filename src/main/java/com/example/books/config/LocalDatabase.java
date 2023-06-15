package com.example.books.config;

import com.example.books.model.Book;
import com.example.books.model.Seller;
import com.example.books.repository.BookRepository;
import com.example.books.repository.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocalDatabase {
    @Bean
    CommandLineRunner initDatabase(BookRepository repository, SellerRepository sellerRepository) {
        System.out.print("daa");
        return args -> {
            log.info("Preloading " + repository.save(new Book(1, "Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Book(2, "Frodo Baggins", "thief")));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
            log.info("Preloading " + sellerRepository.save(new Seller(null,"name","address",null)));
        };
    }
}
