package com.samis.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    public final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(BookPublisher bookPublisher) {
        publisherRepository.save(bookPublisher);


    }

    public List<BookPublisher> getPublishers() {
        return publisherRepository.findAll();
    }
}
