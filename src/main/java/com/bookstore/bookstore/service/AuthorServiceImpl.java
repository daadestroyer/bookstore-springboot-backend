package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(int id) throws ResourceNotFoundException {
        return this.authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public String deleteAuthorById(int id) throws ResourceNotFoundException {
        Optional<Author> optionalAuthor = this.authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            this.authorRepository.deleteById(id);
            return "Author with id " + id + " deleted";
        } else {
            throw new ResourceNotFoundException("Author with id " + id + " not found");
        }
    }

    @Override
    public Author updateAuthor(int authorId, Author newAuthor) throws ResourceNotFoundException {
        Optional<Author> optionalAuthor = this.authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()) {
            throw new ResourceNotFoundException("Author with id " + authorId + " not found");
        } else {
            Author dbAuthor = optionalAuthor.get();
            List<Book> dbBooks = dbAuthor.getBooks();
            newAuthor.setAuthorId(dbAuthor.getAuthorId());
            newAuthor.setBooks(dbAuthor.getBooks());
            return this.authorRepository.save(newAuthor);
        }

    }
}
