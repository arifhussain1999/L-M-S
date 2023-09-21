package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book where genre = :genre and cost > :cost", nativeQuery = true)
     List<Book> listOfBookByGenreAndCost(String genre, int cost);
    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost")
    List<Book> listOfBookByGenreAndCostHQL(Genre genre, int cost);
}