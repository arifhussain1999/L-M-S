package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Book;

public class BookTransformer {
    public static BookResponse booktoBookResponse(Book book){
      return BookResponse.builder().
              authorName(book.getAuthor().getName()).
              genre(book.getGenre()).
              cost(book.getCost()).
              noOfPages(book.getNoOfPages()).
              title(book.getTitle()).build();
    }
}
