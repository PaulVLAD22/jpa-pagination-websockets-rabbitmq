package com.example.books.repository;

import com.example.books.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findByTitle(String title, Pageable pageable);

    List<Book> findByAuthor(String author, Pageable pageable);

    Book save(Book book);
}
