package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> createAuthor(@Valid @RequestBody Author author) {
        Author response = this.authorService.saveAuthor(author);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        List<Author> allAuthors = this.authorService.getAllAuthors();
        if (allAuthors.size() == 0) {
            return new ResponseEntity<>("no author found", HttpStatus.OK);
        }
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) throws ResourceNotFoundException {
        Author author = authorService.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable int id) throws ResourceNotFoundException {
        String message = authorService.deleteAuthorById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<?> updateAuthor(@Valid @RequestParam int authorId, @RequestBody Author newAuthor) throws ResourceNotFoundException {
        Author author = this.authorService.updateAuthor(authorId, newAuthor);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}