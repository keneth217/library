package com.samis.books;

import com.samis.security.ExtractUserNameJwtMyCustom;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WriteOffService {

    public final BookWriteOffRepository bookWriteOffRepository;
    public  final  BookRepository bookRepository;


    public WriteOffService(BookWriteOffRepository bookWriteOffRepository, BookRepository bookRepository) {
        this.bookWriteOffRepository = bookWriteOffRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void writeOffBook(BookWriteOff bookWriteOff, String token) throws IllegalAccessException {
        String username = ExtractUserNameJwtMyCustom.extractUsername(token);
        bookWriteOff.setSchoolName(username);
        String bookRef=bookWriteOff.getBookRef();
        System.out.println(bookWriteOff);

        boolean exist=bookRepository.existsBySchoolNameAndBookRefNo(username,bookRef);
        if(!exist)
        {
            throw  new IllegalAccessException("Book with ref number" + bookRef+" does not exits");
        }else
        {
            bookRepository.deleteBySchoolNameAndbookRefNo(username,bookRef);
        }




        bookWriteOffRepository.save(bookWriteOff);
    }
}
