package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author saveAuthor(Author author);
    Optional<Author> getAuthorById(int id);
    List<Author> getAllAuthors();
    void deleteAuthorById(int id);
}