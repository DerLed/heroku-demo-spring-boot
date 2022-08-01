package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

    private final MessageRepo messageRepo;
    private Integer count = 0;

    @GetMapping
    public List<Message> home(){
        count++;
        Message m = Message.builder()
                .text("Hello" + count).build();
        messageRepo.save(m);
        return messageRepo.findAll();
    }
}
