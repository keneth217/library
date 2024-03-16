package com.samis.books;

import jakarta.persistence.*;

@Entity
@Table
public class BookTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String studentId;
    private String schoolName;
    private String bookRefNo;
    private  String dateBorrowed;
    private  String dateToReturn;
    private  boolean bookIsReturned;

    public BookTransaction(String studentId, String schoolName, String bookRefNo, String dateBorrowed, String dateToReturn, boolean bookIsReturned) {
        this.studentId = studentId;
        this.schoolName = schoolName;
        this.bookRefNo = bookRefNo;
        this.dateBorrowed = dateBorrowed;
        this.dateToReturn = dateToReturn;
        this.bookIsReturned = bookIsReturned;
    }

    public BookTransaction() {
    }

    @Override
    public String toString() {
        return "BookTransaction{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", bookRefNo='" + bookRefNo + '\'' +
                ", dateBorrowed='" + dateBorrowed + '\'' +
                ", dateToReturn='" + dateToReturn + '\'' +
                ", bookIsReturned=" + bookIsReturned +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBookRefNo() {
        return bookRefNo;
    }

    public void setBookRefNo(String bookRefNo) {
        this.bookRefNo = bookRefNo;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(String dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public boolean isBookIsReturned() {
        return bookIsReturned;
    }

    public void setBookIsReturned(boolean bookIsReturned) {
        this.bookIsReturned = bookIsReturned;
    }
}
