package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.BookNotFoundException;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Book findBookById(int bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book Id "+bookId+" not found"));
    }

    @Override
    public String deleteBook(int bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            bookRepository.deleteById(bookId);
            return "Book "+bookId+" deleted";
        }
        throw new BookNotFoundException("Book Id "+bookId+" not found");
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
