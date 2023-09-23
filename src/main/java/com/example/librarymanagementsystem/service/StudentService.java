package com.example.librarymanagementsystem.service;
import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.transformer.LibraryCardTransformer;
import com.example.librarymanagementsystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
@Autowired
StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {

      Student student = StudentTransformer.studentRequestToStudent(studentRequest);   //we are setting in transformer
      LibraryCard card = LibraryCardTransformer.prepareLibraryCard();
      student.setLibraryCard(card);
      card.setStudent(student);
     studentRepository.save(student);  //both student and Lc will be saved

       return StudentTransformer.studentToStudentResponse(student);
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }

    public List<String> getMales() {

        List<String> names= new ArrayList<>();
        List<Student> studentList= studentRepository.findByGender(Gender.MALE);
        for(Student s : studentList){
        names.add(s.getName());
        }
       return names;
    }

}
