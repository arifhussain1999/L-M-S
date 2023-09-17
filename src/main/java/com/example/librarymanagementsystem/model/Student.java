package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int regNo;
    int age;
    String name;
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @OneToOne(mappedBy = "student")
    LibraryCard libraryCard;
}