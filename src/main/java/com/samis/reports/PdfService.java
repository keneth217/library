package com.samis.reports;

import com.samis.books.*;
import com.samis.security.ExtractUserNameJwtMyCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {
    private  final BookRepository bookRepository;
    private final BookWriteOffRepository bookWriteOffRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    public PdfService(BookRepository bookRepository, BookWriteOffRepository bookWriteOffRepository, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.bookWriteOffRepository = bookWriteOffRepository;
        this.transactionRepository = transactionRepository;
    }
    public List<BookTransaction> bookTransaction(String token)
    {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
        List<BookTransaction> notReturnedBooks= transactionRepository.findBybookIsReturnedAndSchoolName(false,username);
        List<BookTransaction> returnedBooks= transactionRepository.findBybookIsReturnedAndSchoolName(true,username);
        List<BookTransaction> allBooks = new ArrayList<>();
        allBooks.addAll(notReturnedBooks);
        allBooks.addAll(returnedBooks);
        return allBooks;
    }
    public List<Book> allBooks(String token) {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
       List<Book> allbooks= bookRepository.findLastByschoolName(username);
        return allbooks;
    }
    public List<BookWriteOff> Writeoff(String token) {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
          List<BookWriteOff> writeOffs= bookWriteOffRepository.findByschoolName(username);
          return writeOffs;
    }
}
