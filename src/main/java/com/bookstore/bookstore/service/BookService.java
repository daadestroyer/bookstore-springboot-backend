package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> addNewBook(int authorId, Book book) throws ResourceNotFoundException;
    Optional<Book> getBookById(int id);
    List<Book> getAllBooks();
    void deleteBookById(int id);

    Book updateBook(Book book) throws ResourceNotFoundException;

    List<Book> findBookByName(String bookName) throws ResourceNotFoundException;
}