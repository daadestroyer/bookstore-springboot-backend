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
    public List<Book> addNewBook(int authorId, Book newBook) throws ResourceNotFoundException {
        Author dbAuthor = this.authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + " not found"));
        List<Book> dbBooks = dbAuthor.getBooks();
        newBook.setAuthor(dbAuthor);
        dbBooks.add(newBook);
        this.authorRepository.save(dbAuthor);
        return dbAuthor.getBooks();

    }

    @Override
    public Book getBookById(int id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public String deleteBookById(int id) throws ResourceNotFoundException {
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        } else {
            bookRepository.deleteById(id);
            return "Book deleted";
        }

    }

    @Override
    public Book updateBook(Book newBook) throws ResourceNotFoundException {
        Optional<Book> optionalBook = this.bookRepository.findById(newBook.getBookId());
        if(!optionalBook.isPresent()){
            throw new ResourceNotFoundException("Book with id " + newBook.getBookId() + " not found");
        }
        else{
            Book dbBook = optionalBook.get();
            Author dbAuthor = dbBook.getAuthor();

            // saving new book
            newBook.setAuthor(dbAuthor);
            newBook.setBookId(dbBook.getBookId());
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