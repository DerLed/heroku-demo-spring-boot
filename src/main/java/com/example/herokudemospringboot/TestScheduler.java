package com.example.herokudemospringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestScheduler {

    private final MessageRepo messageRepo;

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        messageRepo.save(Message.builder().text("DFFFF").build());
    }
}
