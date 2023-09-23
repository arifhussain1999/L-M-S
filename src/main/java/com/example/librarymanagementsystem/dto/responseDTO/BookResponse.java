package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    String title;

    int noOfPages;

    Genre genre;

    int cost;

    String authorName;
}
