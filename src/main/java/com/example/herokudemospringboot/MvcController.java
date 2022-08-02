package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import reactor.core.publisher.Mono;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MvcController {

    private final MessageRepo messageRepo;
    private final WebClient client;

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

    @PostMapping("test")
    public String test(Model model) throws Exception {

        String URL = "https://cbr.ru/scripts/XML_daily.asp";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);

        doc.getDocumentElement().normalize();


        NodeList nodeList =  doc.getElementsByTagName("Valute");

        for (int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeList.item(i).getTextContent());
        }

        return "redirect:/";
    }


}
