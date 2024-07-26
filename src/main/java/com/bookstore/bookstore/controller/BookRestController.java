package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.BookNotFoundException;
import com.bookstore.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save-book")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveDepartment(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-book")
    public List<Book> getAllBook() {
        return bookService.findAllBooks();
    }

    @GetMapping("/get-book-by-id/{bookId}")
    public Book getBookById(@PathVariable int bookId) throws BookNotFoundException {
        return bookService.findBookById(bookId);
    }

    @DeleteMapping("/delete-book/{bookId}")
    public String deleteBookById(@PathVariable int bookId) throws BookNotFoundException {
        return bookService.deleteBook(bookId);
    }

    @PutMapping("update-book/{bookId}")
    public Book deleteBook(@PathVariable int bookId, @RequestBody Book book) {
        return bookService.updateBook(bookId, book);
    }

    @GetMapping("/get-book-by-name/{name}")
    public List<Book> getBookByName(@PathVariable String name) {
        return bookService.getBookByName(name);
    }
}

