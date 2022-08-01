package com.example.herokudemospringboot;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "message")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
}
