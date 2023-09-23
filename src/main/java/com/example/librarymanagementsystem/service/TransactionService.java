package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
@Autowired
    StudentRepository studentRepository;

@Autowired
    BookRepository bookRepository;
@Autowired
    TransactionRepository transactionRepository;
    public IssueBookResponse issueBook(int bookId, int studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new StudentNotFoundException("Invalid Student Id");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalStudent.isEmpty()){
            throw new BookNotAvailableException("Invalid Book Id");
        }
        Book book = optionalBook.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("Book is Already Issued");
        }

        Student student = optionalStudent.get();   //issue the book

                Transaction transaction = Transaction.builder().     //create transaction
                transactionNumber(String.valueOf(UUID.randomUUID())).
                transactionStatus(TransactionStatus.SUCCESS).
                book(book).
                libraryCard(student.getLibraryCard()).
                build();

                Transaction savedTransaction = transactionRepository.save(transaction);

                //update book
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        //card changes
        student.getLibraryCard().getTransactionList().add(savedTransaction);

        Book savedBook = bookRepository.save(book);  //will save book and transaction
        Student savedStudent = studentRepository.save(student);  //will save student and transaction

        return IssueBookResponse.builder().
                transactionNumber(savedTransaction.getTransactionNumber()).
                transactionStatus(savedTransaction.getTransactionStatus()).
                transactionTime(savedTransaction.getTransactionTime()).
                bookName(savedBook.getTitle()).
                authorName(savedBook.getAuthor().getName()).
                studentName(savedStudent.getName()).
                libraryCardNumber(savedStudent.getLibraryCard().getCardNo()).build();
    }
}
