package com.example.herokudemospringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    private Integer count = 0;

    @GetMapping
    public String home(){
        count++;
        return "Hello Heroku, I am here #" + count;
    }
}
