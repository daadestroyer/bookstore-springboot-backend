package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/{authorId}")
    public ResponseEntity<?> createBook(@Valid @PathVariable int authorId, @RequestBody Book book) throws ResourceNotFoundException {
        List<Book> books = bookService.saveBook(authorId, book);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable int id) {
        Optional<Book> book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}