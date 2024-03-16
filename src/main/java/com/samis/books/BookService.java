package com.samis.books;

import com.samis.security.ExtractUserNameJwtMyCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private  final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<String> addBooks(Book book, String token) {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
        Number noOfBooks = book.getNoBookReceived();
        String bookName = book.getBookName();
        String SubjectName=book.getSubjectName();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 1, sort);
        List<Book> dbBooks = bookRepository.findLastBysubjectNameAndschoolNamebookName(SubjectName, username,bookName, pageable);
        System.out.println(SubjectName + " " + username);
        final Number lastNumber;
        String refno;
        if (!dbBooks.isEmpty()) {
            Book lastBook = dbBooks.get(0);
            String bookRefNo = lastBook.getBookRefNo();
            String firstNumber = bookRefNo.substring(0, bookRefNo.indexOf('/'));
            int extractedNumber = Integer.parseInt(firstNumber);
            lastNumber = extractedNumber+1;
            System.out.println(lastBook);
        } else {
            lastNumber = 0;
            System.out.println("No book found");
        }

        for (int i = 0; i < noOfBooks.intValue(); i++) {
            Book newBook = new Book();
            newBook.setBookName(book.getBookName());
            newBook.setClassForm(book.getClassForm());
            newBook.setSubjectName(book.getSubjectName());
            newBook.setNoBookReceived((Integer) book.getNoBookReceived());
            newBook.setDateReceive(book.getDateReceive());
            newBook.setSchoolName(username);
            int currentYear = Year.now().getValue();
            refno=lastNumber.intValue() + i + "/" + book.getBookName()+"/F"+book.getClassForm() + "/" + currentYear;
            newBook.setBookRefNo(refno);
            System.out.println(newBook);
            bookRepository.save(newBook);
        }
        return List.of(book.getNoBookReceived().toString()+" added Successfully","Last book added");
    }

    public List<Book> getBooks(Book book, String token) {
        String username=ExtractUserNameJwtMyCustom.extractUsername (token);
        List<Book> books= bookRepository.findBySchoolNameAndSubjectNameAndClassForm(username, book.getSubjectName(), book.getClassForm());
        System.out.println(books);
        return  books;
    }
}
