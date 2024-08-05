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
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/{authorId}")
    public ResponseEntity<?> addNewBook(@Valid @PathVariable int authorId, @RequestBody Book book) throws ResourceNotFoundException {
        List<Book> books = bookService.addNewBook(authorId, book);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) throws ResourceNotFoundException {
        Book book = bookService.getBookById(Integer.parseInt(id));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody Book book) throws ResourceNotFoundException {
        this.bookService.updateBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findAllBooksWithThiName(@RequestParam String bookName) throws ResourceNotFoundException {
        List<Book> books = this.bookService.findBookByName(bookName);
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBooks(@PathVariable int bookId) throws ResourceNotFoundException {
        String message = this.bookService.deleteBookById(bookId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}