package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book) {
//first check author is present or not
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid authorId");
        }
        Author author=authorOptional.get();
        book.setAuthor(author);

        author.getBooks().add(book);

        authorRepository.save(author);  //save both author and book, that's why not added book repo
        return "Book added successfully";
    }

    public String deleteBook(int idNo) {
        //first check if book is present or not
        Optional<Book> bookOptional = bookRepository.findById(idNo);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        Book bookOp = bookOptional.get();
        bookRepository.delete(bookOp);
        return "Book deleted successfully";
    }
}