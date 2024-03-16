package com.samis.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookWriteOffRepository  extends JpaRepository<BookWriteOff,Long> {
    List<BookWriteOff> findByschoolName(String username);
    @Modifying
    @Query("DELETE FROM BookWriteOff b WHERE b.schoolName = :schoolName AND b.bookRef = :bookRef")
    void deleteBySchoolNameAndBookref(@Param("schoolName") String schoolName, @Param("bookRef") String bookRef);
    boolean existsBySchoolNameAndBookRef(String schoolName, String bookRef);
}
