package com.samis.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/books/")
public class BookController {

    private final BookService bookService;
    private final BookStatisticsService bookStatisticsService;

    @Autowired
    public BookController(BookService bookService, BookStatisticsService bookStatisticsService) {
        this.bookService = bookService;
        this.bookStatisticsService = bookStatisticsService;
    }
    @PostMapping("/addbooks")
    public List<String> postBooks(@RequestBody Book book, @RequestHeader("Authorization") String token) {
       return  bookService.addBooks(book, token);
    }
    @GetMapping("/getstatistics")
    public Map<String, Integer> getBooksStatistics(@RequestHeader("Authorization") String token) {
        return  bookStatisticsService.getStatistics(token);
//        return null;
    }

    @PostMapping("/getBooks")
    public List<Book> getBooks(@RequestBody Book book, @RequestHeader("Authorization") String token)
    {
       return bookService.getBooks(book,token);

    }
}
