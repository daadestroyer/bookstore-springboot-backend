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
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable String id) {
        Optional<Book> book = bookService.getBookById(Integer.parseInt(id));
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

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody Book book) throws ResourceNotFoundException {
        this.bookService.updateBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findAllBooksWithThiName(@RequestParam String bookName) throws ResourceNotFoundException {
        List<Book> books = this.bookService.findBookByName(bookName);
       return new ResponseEntity<>(books,HttpStatus.OK);

    }
}