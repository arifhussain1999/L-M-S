package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setGender(studentRequest.getGender());
        student.setEmail(studentRequest.getEmail());


        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        Student savedStudent= studentRepository.save(student);  //both student and Lc will be saved

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(savedStudent.getName());
        studentResponse.setEmail(savedStudent.getEmail());
        studentResponse.setMessage("You have been registered");

        LibraryCardResponse cardResponse = new LibraryCardResponse();
        cardResponse.setCardNo(savedStudent.getLibraryCard().getCardNo());
        cardResponse.setCardStatus(savedStudent.getLibraryCard().getCardStatus());
        cardResponse.setIssueDate(savedStudent.getLibraryCard().getIssueDate());

        studentResponse.setLibraryCardResponse(cardResponse);

        return studentResponse;
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
