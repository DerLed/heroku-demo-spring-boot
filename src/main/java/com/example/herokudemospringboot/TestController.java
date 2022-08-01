package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

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
