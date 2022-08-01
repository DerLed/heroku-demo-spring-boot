package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Message newMessage(@RequestBody Message message){
        return messageRepo.save(message);
    }
}
