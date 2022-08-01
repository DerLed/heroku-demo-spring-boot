package com.example.herokudemospringboot;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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


    private LocalDateTime createTime;

    @PrePersist
    private void create(){
        if(createTime == null) {
            this.createTime = LocalDateTime.now();
        }
    }
}
