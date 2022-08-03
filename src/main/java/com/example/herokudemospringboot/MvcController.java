package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MvcController {

    private final MessageRepo messageRepo;
    private final WebClient client;

    @GetMapping
    public String index(Model model){

        model.addAttribute("messages", messageRepo.findAll(Sort.by(Sort.Order.desc("createTime"))));
        return "index";
    }

    @PostMapping("/message/del/{id}")
    public String delete(@PathVariable Long id, Model model){
        try {
            messageRepo.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @PostMapping("/message")
    public String newMessage(@RequestParam String message){
        messageRepo.save(Message.builder()
                        .text(message)
                .build());
        return "redirect:/";
    }


    @PostMapping("test")
    public String test(Model model) throws Exception {



        return "redirect:/";
    }


}
