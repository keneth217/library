package com.samis.books;

import com.samis.books.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.subjectName = :subjectName AND b.schoolName = :schoolName AND b.bookName=:bookName ORDER BY b.id DESC")
    List<Book> findLastBysubjectNameAndschoolNamebookName(@Param("subjectName") String subjectName, @Param("schoolName") String schoolName,@Param("bookName") String bookName,Pageable pageable);

    List<Book> findLastByschoolName(String username);

    @Modifying
    @Query("DELETE FROM Book b WHERE b.schoolName = :schoolName AND b.bookRefNo = :bookRefNo")
    void deleteBySchoolNameAndbookRefNo(@Param("schoolName") String schoolName, @Param("bookRefNo") String bookRefNo);
    boolean existsBySchoolNameAndBookRefNo(String schoolName, String bookRefNo);

    List<Book> findBySchoolNameAndSubjectNameAndClassForm(String schoolName,String subjectName,String classForm);


    ;
}
