package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
@Autowired
StudentRepository studentRepository;

    public Student addStudent(Student student) {
        LibraryCard libraryCard = new LibraryCard();
       libraryCard.getCardNo();
        Student savedStudent= studentRepository.save(student);
        return savedStudent;
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }
}
