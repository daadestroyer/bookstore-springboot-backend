package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;

import java.util.List;

public interface BookService {
    public Book saveBook(Book book) throws ResourceNotFoundException;

    Book saveBookForExistingAuthor(String email, Book book) throws ResourceNotFoundException;

    List<Book> findAllBooks();

    Book findBookById(int bookId) throws ResourceNotFoundException, ResourceNotFoundException;

    String deleteBook(int bookId) throws ResourceNotFoundException, ResourceNotFoundException;

    Book updateBook(int bookId, Book book);


    List<Book> getBookByName(String name);

    Book getBookByBookCode(String bookCode) throws ResourceNotFoundException;


}
