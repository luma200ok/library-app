package com.group.libraryapp.dto.book.requeset;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookLoanRequest {

    private String userName;
    private String bookName;
}
