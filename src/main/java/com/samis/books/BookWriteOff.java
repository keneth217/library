package com.samis.books;

import jakarta.persistence.*;

@Entity
@Table
public class BookWriteOff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String schoolName;
    private String whyWriteOff;
    private String bookRef;
    private String dateWriteOff;

    public BookWriteOff(String schoolName, String whyWriteOff, String bookRef, String dateWriteOff) {
        this.schoolName = schoolName;
        this.whyWriteOff = whyWriteOff;
        this.bookRef = bookRef;
        this.dateWriteOff = dateWriteOff;
    }

    public BookWriteOff() {
    }


    @Override
    public String toString() {
        return "BookWriteOff{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", whyWriteOff='" + whyWriteOff + '\'' +
                ", bookRef='" + bookRef + '\'' +
                ", dateWriteOff='" + dateWriteOff + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getWhyWriteOff() {
        return whyWriteOff;
    }

    public void setWhyWriteOff(String whyWriteOff) {
        this.whyWriteOff = whyWriteOff;
    }

    public String getBookRef() {
        return bookRef;
    }

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    public String getDateWriteOff() {
        return dateWriteOff;
    }

    public void setDateWriteOff(String dateWriteOff) {
        this.dateWriteOff = dateWriteOff;
    }
}

