package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.requeset.BookCreateRequest;
import com.group.libraryapp.dto.book.requeset.BookLoanRequest;
import com.group.libraryapp.dto.book.requeset.BookReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final UserRepository userRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final BookRepository bookRepository;

    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    public void loanBook(BookLoanRequest request) {
//      *  1. 책 정보 가져오기.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

//      *  2. 대출기록 정보 확인 (대출 유무 확인)
//      *  3. 대출 중이라면 예외 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false)) {
            throw new IllegalArgumentException("이미 대출된 책입니다.");
        }

//      *  4. 유저 정보 가져오기.
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
//      *  5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
    }

    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
    }
}
