package com.example.herokudemospringboot.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {



    @Bean
    public WebClient currencyCBRClient(){
        return WebClient.builder()
//                .baseUrl("https://cbr.ru")
                .baseUrl("http://www.mocky.io")

//                .defaultCookie("cookieKey", "__ddg1_=SH4RXYpzlfxttm7GzZuH")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE, "charset=utf-8")
                .build();
//        return WebClient.create("http://localhost:8080");


    }
}
