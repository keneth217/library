package com.samis.books;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class BookPublisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String publisher;
    private LocalDate dateAdded;

    @PrePersist
    protected void onCreate() {
        if (this.dateAdded == null) {
            this.dateAdded = LocalDate.now();
        }
    }
    public BookPublisher(Long id, String publisher, LocalDate dateAdded) {
        this.id = id;
        this.publisher = publisher;
        this.dateAdded = dateAdded;
    }

    public BookPublisher(String publisher, LocalDate dateAdded) {
        this.publisher = publisher;
        this.dateAdded = dateAdded;
    }

    public BookPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BookPublisher() {
    }

    @Override
    public String toString() {
        return "BookPublisher{" +
                "id=" + id +
                ", publisher='" + publisher + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }


}
