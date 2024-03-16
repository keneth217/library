package com.samis.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<BookTransaction,Long> {


    List<BookTransaction> findBybookIsReturnedAndSchoolName(boolean b,String username);
}
