package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.exception.ResourceNotFoundException;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> addNewBook(int authorId, Book newBook) throws ResourceNotFoundException {
        // fetch the existing owner
        Author dbAuthor = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + " not found"));
        List<Book> dbBooks = dbAuthor.getBooks();
        newBook.setAuthor(dbAuthor);
        dbBooks.add(newBook);
        dbAuthor.setBooks(dbBooks);
        Author savedAuthor = authorRepository.save(dbAuthor);
        return dbAuthor.getBooks(); // return the total books present now in db for this author
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

    @Override
    public Book updateBook(Book newBook) throws ResourceNotFoundException {
        // first check this book exist or not
        Optional<Book> optionalBook = this.bookRepository.findById(newBook.getBookId());
        if (!optionalBook.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + newBook.getBookId() + " not found");
        } else {
            // if this book exist then fetch this book
            Book dbBook = optionalBook.get();

            newBook.setBookId(dbBook.getBookId());
            newBook.setAuthor(dbBook.getAuthor());
            return this.bookRepository.save(newBook);
        }

    }

    @Override
    public List<Book> findBookByName(String bookName) throws ResourceNotFoundException {
        List<Book> books = this.bookRepository.findByBookNameContainingIgnoreCase(bookName);
        if (books.size() == 0) {
            throw new ResourceNotFoundException("Book with name " + bookName + " not found");
        } else {
            return books;
        }
    }


}