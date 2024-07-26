package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.BookNotFoundException;

import java.util.List;

public interface BookService {
    public Book saveDepartment(Book book);

    List<Book> findAllBooks();

    Book findBookById(int bookId) throws BookNotFoundException;

    String deleteBook(int bookId) throws BookNotFoundException;

    Book updateBook(int bookId, Book book);


    List<Book> getBookByName(String name);
}
