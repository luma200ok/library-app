package com.group.libraryapp.dto.book.requeset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookReturnRequest {
    private String userName;
    private String bookName;
}
