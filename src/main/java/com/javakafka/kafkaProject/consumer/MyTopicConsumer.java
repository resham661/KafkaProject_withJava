package com.javakafka.kafkaProject.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class MyTopicConsumer {

    private final List<String> messages = new ArrayList<>();
    private CountDownLatch latch = new CountDownLatch(1);
    //private String payload;

    @KafkaListener(topics = "myTopic", groupId = "kafka-sandbox")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public CountDownLatch getLatch() {
        return latch;
      }

//    public String getPayload() {
//        return payload;
//    }
}