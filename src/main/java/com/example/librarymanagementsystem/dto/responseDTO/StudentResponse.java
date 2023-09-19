package com.example.librarymanagementsystem.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {

    String name;
    String email;
    String message;
    String issuedCardNo;
}
