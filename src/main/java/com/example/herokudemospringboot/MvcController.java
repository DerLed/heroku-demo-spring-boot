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

        String URL = "https://cbr.ru/scripts/XML_daily.asp";

        List<Valute> valCurs = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);

        doc.getDocumentElement().normalize();


        NodeList nodeList =  doc.getElementsByTagName("Valute");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "###000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        for (int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeList.item(i).getTextContent());

            Node node = nodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                Valute valute = new Valute(
                        elem.getElementsByTagName("NumCode").item(0).getTextContent(),
                        elem.getElementsByTagName("CharCode").item(0).getTextContent(),
                        Integer.parseInt(elem.getElementsByTagName("Nominal").item(0).getTextContent()),
                        elem.getElementsByTagName("Name").item(0).getTextContent(),
                        (BigDecimal) decimalFormat.parse(elem.getElementsByTagName("Value").item(0).getTextContent())
                );

                valCurs.add(valute);
            }
        }

        valCurs.forEach(System.out::println);

        return "redirect:/";
    }


}
