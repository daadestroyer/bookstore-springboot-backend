package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface BookService {
    List<Book> addNewBook(int authorId, Book book) throws ResourceNotFoundException;

    Book getBookById(int id) throws ResourceNotFoundException;

    List<Book> getAllBooks();

    String deleteBookById(int id) throws ResourceNotFoundException;

    Book updateBook(Book newBook) throws ResourceNotFoundException;

    List<Book> findBookByName(String bookName) throws ResourceNotFoundException;
}