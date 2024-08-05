package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.Author;
import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Author saveAuthor(Author author) {
        Optional<Author> optionalAuthor = this.authorRepository.findById(author.getAuthorId());
        // saving new author
        if(!optionalAuthor.isPresent()){
            return this.authorRepository.save(author);
        }
        else{
            // if author is present just add new books if any
            Author dbAuthor = optionalAuthor.get();
            List<Book> dbBookList = dbAuthor.getBooks();
            List<Book> newBooks = author.getBooks();

            // fetching each new book
            for(Book book : newBooks){
                // for each new book setting author
                book.setAuthor(dbAuthor);

                // adding new book to our db book list
                dbBookList.add(book);
            }
            dbAuthor.setBooks(dbBookList);
            return this.authorRepository.save(dbAuthor);
        }
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }
}