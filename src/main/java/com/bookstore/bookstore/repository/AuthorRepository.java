package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    public List<Book> findByAuthorNameContainingIgnoreCase(String authorName);
    public Author findByAuthorEmail(String email);
}
