package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

//@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String cardNo;
    CardStatus cardStatus;
    @CreatedDate
    Date issueDate;
    @OneToOne
    @JoinColumn
    Student student;

}
