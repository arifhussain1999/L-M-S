package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        try {
            String response = bookService.addBook(book);
            return response;
        }
        catch (AuthorNotFoundException e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity deleteBook(@RequestParam("id") int idNo){
        try {
            String response = bookService.deleteBook(idNo);
            return new ResponseEntity<>("Book deleted", HttpStatus.OK);
        }
        catch (BookNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
