package com.samis.books;

import com.samis.security.ExtractUserNameJwtMyCustom;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private  final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void givebook(BookTransaction bookTransaction, String token) {
        String username= ExtractUserNameJwtMyCustom.extractUsername(token);
        bookTransaction.setSchoolName(username);
        bookTransaction.setBookIsReturned(false);
        transactionRepository.save(bookTransaction);





    }
    @Transactional
    public void takeBook(BookTransaction bookTransaction, String token) throws IllegalAccessException {
        String username =ExtractUserNameJwtMyCustom.extractUsername(token);
        bookTransaction.setSchoolName(username);
            Long id=bookTransaction.getId();
            // Check if the book exists
            Optional<BookTransaction> existingBook = transactionRepository.findById(id);
            if (existingBook.isEmpty()) {
                throw new IllegalAccessException("Book with reference number " + id + " does not exist");
            }
            // Retrieve the book object from the request body
            BookTransaction updatedBook = existingBook.get();
            updatedBook.setBookIsReturned(true);
            transactionRepository.save(updatedBook);
        }

    public List<BookTransaction> borrowedBooks(String token) {
        String username=ExtractUserNameJwtMyCustom.extractUsername(token);
        return transactionRepository.findBybookIsReturnedAndSchoolName(false,username);

    }
}
