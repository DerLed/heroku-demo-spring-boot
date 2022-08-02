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
////        ResponseSpec rs = client.get().uri("/scripts/XML_daily.asp").retrieve();
//        ResponseSpec rs = client.get().uri("/v2/5c8bdd5c360000cd198f831e").retrieve();
//        Mono<String> mn = rs.bodyToMono(String.class);
//        String ob = mn.blockOptional().orElseThrow();

//
//        Document d = client
//                .get()
//                .uri("/scripts/XML_daily_eng.asp")
////                .accept(MediaType.APPLICATION_XML)
////                .acceptCharset(Charset.forName("windows-1251"))
////                .accept(MediaType.APPLICATION_XML)
//                .retrieve().bodyToMono(Document.class)
//                .block();

//        ResponseSpec rs = client.get().uri("/scripts/XML_daily.asp").retrieve();
//        UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
//        RequestBodySpec bodySpec = uriSpec.uri("/scripts/XML_daily.asp");
//        try {
//            Object[] objects = response.block();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
////
//        Document doc = documentBuilder
//                .parse(new URL("http://www.mocky.io/v2/5c8bdd5c360000cd198f831e").openStream());
//////        assert d != null;
//        doc.getDocumentElement().normalize();
//
//        NodeList nodeList = doc.getElementsByTagName("student");
////
//////        System.out.println(d.getXmlVersion());
////
////
////            InputStream is = new ByteArrayInputStream(ob.getBytes());
////            Document document = documentBuilder.parse(is);



//        System.out.println(doc.getXmlVersion());


        String URL = "https://cbr.ru/scripts/XML_daily.asp";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);

        // normalize XML response
        doc.getDocumentElement().normalize();

        //read course details first
//        Integer aaa = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());

//        String wwww =  doc.getElementsByTagName("title").item(0).getTextContent();

        NodeList nodeList =  doc.getElementsByTagName("Valute");

        for (int i = 0; i < nodeList.getLength(); i++){
            System.out.println(nodeList.item(i).getTextContent());
        }
//
//         Double yyy = Double.parseDouble(doc.getElementsByTagName("price").item(0).getTextContent());
//          Date tttt = new SimpleDateFormat("yyyy-MM-dd").parse(doc.getElementsByTagName("created").item(0).getTextContent());


//        //read students list
//        NodeList nodeList = doc.getElementsByTagName("student");
//
//        //create an empty list for students
//        List<Student> students = new ArrayList<>();
//
//        //loop all available student nodes
//        for (int i = 0; i < nodeList.getLength(); i++) {
//
//            Node node = nodeList.item(i);
//
//            if(node.getNodeType() == Node.ELEMENT_NODE) {
//                Element elem = (Element) node;
//                Student student = new Student(
//                        Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent()),
//                        elem.getElementsByTagName("first_name").item(0).getTextContent(),
//                        elem.getElementsByTagName("last_name").item(0).getTextContent(),
//                        elem.getElementsByTagName("avatar").item(0).getTextContent()
//                );
//                students.add(student);
//            }
//        }


        return "redirect:/";
    }


}
