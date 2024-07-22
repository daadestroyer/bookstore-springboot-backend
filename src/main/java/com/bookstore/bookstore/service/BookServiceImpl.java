package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveDepartment(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public String deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
        return "book deleted";
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book savedBook = bookRepository.findById(bookId).get();
        savedBook.setBookName(book.getBookName());
        savedBook.setBookRating(book.getBookRating());
        savedBook.setBookCode(book.getBookCode());
        Book updatedBook = bookRepository.save(savedBook);
        return updatedBook;
    }

    @Override
    public List<Book> getBookByName(String name) {
        return bookRepository.findByBookNameContainingIgnoreCase(name);
    }


}
