package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> saveBook(int authorId, Book book) throws ResourceNotFoundException;
    Optional<Book> getBookById(int id);
    List<Book> getAllBooks();
    void deleteBookById(int id);
}