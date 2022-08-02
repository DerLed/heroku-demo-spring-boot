package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MvcController {

    private final MessageRepo messageRepo;

    @GetMapping
    public String index(Model model){
        model.addAttribute("messages", messageRepo.findAll());
        return "index";
    }

    @PostMapping("/message/del/{id}")
    public String delete(@PathVariable Long id, Model model){
        messageRepo.deleteById(id);
        return "redirect:/";
    }
}
