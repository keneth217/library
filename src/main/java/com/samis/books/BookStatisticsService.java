package com.samis.books;

import com.samis.security.ExtractUserNameJwtMyCustom;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookStatisticsService {

    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;
    private final  BookWriteOffRepository bookWriteOffRepository;

    public BookStatisticsService(BookRepository bookRepository, TransactionRepository transactionRepository, BookWriteOffRepository bookWriteOffRepository) {
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
        this.bookWriteOffRepository = bookWriteOffRepository;
    }

    public LocalDate dateFormatter(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    public Map<String, Integer> getStatistics(String token) {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
        System.out.println("------------------------------------username-------------------------");
        System.out.println(username);
        System.out.println(token);
        System.out.println("------------------------------------username-------------------------");
        List<Book> bookList = bookRepository.findLastByschoolName(username);
        List<BookTransaction> borrowedBook = transactionRepository.findBybookIsReturnedAndSchoolName(false,username);
        List<BookWriteOff> writtenOffBooks=bookWriteOffRepository.findByschoolName(username);
        LocalDate currentDate = LocalDate.now();
        List<BookTransaction> overdueBooks = borrowedBook.stream()
                .filter(thebook -> dateFormatter(thebook.getDateToReturn()).isBefore(currentDate))
                .toList();
        int offBooks=writtenOffBooks.size();
        int bookNotReturned=overdueBooks.size();
        int numberOfBooks = bookList.size();
        int numberOfBorrowedBooks = borrowedBook.size();

        Map<String, Integer> booksStatistics = new HashMap<>();
        booksStatistics.put("offBooks", offBooks);
        booksStatistics.put("bookNotReturned", bookNotReturned);
        booksStatistics.put("numberOfBooks", numberOfBooks);
        booksStatistics.put("numberOfBorrowedBooks", numberOfBorrowedBooks);

        return booksStatistics;
    }
}
