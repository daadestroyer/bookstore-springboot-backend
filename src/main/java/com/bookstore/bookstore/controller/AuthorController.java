package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
        Author createdAuthor = authorService.saveAuthor(author);
        return ResponseEntity.ok(createdAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) throws ResourceNotFoundException {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable int id) throws ResourceNotFoundException {
        String message = authorService.deleteAuthorById(id);
        return ResponseEntity.ok(message);
    }
}