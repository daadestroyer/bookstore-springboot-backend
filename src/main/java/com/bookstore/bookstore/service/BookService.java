package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    public Book saveDepartment(Book book);

    List<Book> findAllBooks();

    Book findBookById(int bookId);

    String deleteBook(int bookId);

    Book updateBook(int bookId, Book book);


    List<Book> getBookByName(String name);
}
