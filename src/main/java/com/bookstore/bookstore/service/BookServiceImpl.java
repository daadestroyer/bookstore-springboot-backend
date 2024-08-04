package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book saveBook(Book book) throws ResourceNotFoundException {
        return this.bookRepository.save(book);
    }

    @Override
    public Book saveBookForExistingAuthor(String email, Book book) throws ResourceNotFoundException {
        return null;
    }


    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(int bookId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + bookId + " not found"));
    }

    @Override
    public String deleteBook(int bookId) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + bookId + " not found"));

        bookRepository.delete(book);
        return "Book with ID " + bookId + " has been deleted";
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        // Implementation for updating a book
        return null;
    }

    @Override
    public List<Book> getBookByName(String name) {
        // Implementation for finding books by name
        return null;
    }

    @Override
    public Book getBookByBookCode(String bookCode) throws ResourceNotFoundException {
        return bookRepository.findByBookCode(bookCode)
                .orElseThrow(() -> new ResourceNotFoundException("Book with book code " + bookCode + " not found"));
    }
}