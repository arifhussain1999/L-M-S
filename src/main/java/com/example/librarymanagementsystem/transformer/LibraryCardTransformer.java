package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.model.LibraryCard;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard prepareLibraryCard() {
      return LibraryCard.builder().
                cardStatus(CardStatus.ACTIVE).
                cardNo(String.valueOf(UUID.randomUUID())).build();
    }
}
