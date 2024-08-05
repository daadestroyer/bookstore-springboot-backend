package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Book> saveBook(int authorId, Book newBook) throws ResourceNotFoundException {
        // fetch the existing owner
        Author dbAuthor = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + " not found"));
        List<Book> dbBooks = dbAuthor.getBooks();
        newBook.setAuthor(dbAuthor);
        dbBooks.add(newBook);
        dbAuthor.setBooks(dbBooks);
        Author savedAuthor = authorRepository.save(dbAuthor);
        return dbAuthor.getBooks();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }
}