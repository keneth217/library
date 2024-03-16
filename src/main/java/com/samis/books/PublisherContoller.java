package com.samis.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherContoller {


    private final  PublisherService publisherService;
    @Autowired
    public PublisherContoller(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @PostMapping("/add")
    public void addPublisher(@RequestBody BookPublisher bookPublisher)
    {
        publisherService.addPublisher(bookPublisher);

    }
    @GetMapping("/fetch")
    public List<BookPublisher> getPublishers()
    {
        return publisherService.getPublishers();

    }

}
