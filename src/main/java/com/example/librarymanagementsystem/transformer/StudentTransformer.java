package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.Student;

public class StudentTransformer {
    public static Student studentRequestToStudent(StudentRequest studentRequest){
        return Student.builder().      //pro's way to create object and set
                name(studentRequest.getName()).
                age(studentRequest.getAge()).
                email(studentRequest.getEmail()).
                gender(studentRequest.getGender()).build();
    }
    public static StudentResponse studentToStudentResponse(Student student){

        LibraryCardResponse cardResponse = new LibraryCardResponse();
        cardResponse.setCardNo(student.getLibraryCard().getCardNo());
        cardResponse.setCardStatus(student.getLibraryCard().getCardStatus());
        cardResponse.setIssueDate(student.getLibraryCard().getIssueDate());


    return StudentResponse.builder().
            name(student.getName()).
            email(student.getEmail()).
            libraryCardResponse(cardResponse).build();
    }
}
