package com.samis.books;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/write_off")
public class WriteOffController {
    public final WriteOffService writeOffService;

    public WriteOffController(WriteOffService writeOffService) {
        this.writeOffService = writeOffService;
    }

    @PostMapping("/")
    public void postWriteoff(@RequestBody BookWriteOff bookWriteOff,@RequestHeader("Authorization") String token) throws IllegalAccessException {
        writeOffService.writeOffBook(bookWriteOff,token);

    }

}
