package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> saveNewBookAndNewAuthor(@Valid @RequestBody Book book) throws ResourceNotFoundException {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PostMapping("/book/{email}")
    public ResponseEntity<?> saveNewBookForExistingAuthor(@Valid @PathVariable String email, @RequestBody Book book) throws ResourceNotFoundException {
        Book savedBook = bookService.saveBookForExistingAuthor(email , book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/book")
    public ResponseEntity<?> getAllBooks() {
        List<Book> allBooks = bookService.findAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/book/{bookCode}")
    public ResponseEntity<?> getBookByBookCode(@PathVariable String bookCode) throws ResourceNotFoundException {
        Book savedBook = bookService.getBookByBookCode(bookCode);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }
}