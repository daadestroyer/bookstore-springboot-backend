package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(Author author);
    Author getAuthorById(int id) throws ResourceNotFoundException;
    List<Author> getAllAuthors();
    String deleteAuthorById(int id) throws ResourceNotFoundException;
}