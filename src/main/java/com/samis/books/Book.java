package com.samis.books;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String classForm;
    private String subjectName;
    private int noBookReceived;
    private String dateReceive;
    private String schoolName;
    private String bookRefNo;

    public Book(String bookName, String classForm, String subjectName, int noBookReceived, String dateReceive, String schoolName, String bookRefNo) {
        this.bookName = bookName;
        this.classForm = classForm;
        this.subjectName = subjectName;
        this.noBookReceived = noBookReceived;
        this.dateReceive = dateReceive;
        this.schoolName = schoolName;
        this.bookRefNo = bookRefNo;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", classForm='" + classForm + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", noBookReceived='" + noBookReceived + '\'' +
                ", dateReceive='" + dateReceive + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", bookRefNo='" + bookRefNo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getClassForm() {
        return classForm;
    }

    public void setClassForm(String classForm) {
        this.classForm = classForm;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Number getNoBookReceived() {
        return noBookReceived;
    }

    public void setNoBookReceived(int noBookReceived) {
        this.noBookReceived = noBookReceived;
    }

    public String getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(String dateReceive) {
        this.dateReceive = dateReceive;
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
}

