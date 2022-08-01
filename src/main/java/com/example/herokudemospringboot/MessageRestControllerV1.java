package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageRestControllerV1 {

    private final MessageRepo messageRepo;

    @GetMapping
    public List<Message> home(){
        return messageRepo.findAll();
    }

    @GetMapping("/{id}")
    public Message one(@PathVariable Long id){
        return messageRepo.findById(id).orElseThrow();
    }

    @PostMapping
    public Message newMessage(@RequestBody Message message){
        return messageRepo.save(message);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        messageRepo.deleteById(id);
    }


}
