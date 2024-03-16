package com.samis.books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private  final  TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/givebook")
    public String postgiveBook(@RequestBody BookTransaction bookTransaction,@RequestHeader("Authorization") String token)
    {
        transactionService.givebook(bookTransaction,token);
        return null;

    }
    @PostMapping("/takebook")
    public String postTakeBook(@RequestBody BookTransaction bookTransaction,@RequestHeader("Authorization") String token) throws IllegalAccessException {
        transactionService.takeBook(bookTransaction,token);
        return null;

    }
    @GetMapping("/borrowedBooks")
    public List<BookTransaction> borowedBooks(@RequestHeader("Authorization") String token)
    {
        return transactionService.borrowedBooks(token);
    }

}
