//Java_in_use_topic
package com.javakafka.kafkaProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javakafka.kafkaProject.consumer.MyTopicConsumer;

@RestController
public class KafkaController {

	private static final Logger log = LoggerFactory.getLogger(KafkaController.class);

    private KafkaTemplate<String, String> template;
    private MyTopicConsumer myTopicConsumer;

    private final String topicName = "myTopic";

    public KafkaController(KafkaTemplate<String, String> template, MyTopicConsumer myTopicConsumer) {
        this.template = template;
        this.myTopicConsumer = myTopicConsumer;
    }

    @GetMapping("/kafka/produce")
    public String produce(@RequestParam String message) {
    	template.send(topicName, message)
        .addCallback(this::onSuccess, this::onFailure);
    	return "Successfully send the message";
    }
    
    private void onSuccess(SendResult<String, String> result) {
        log.info("Message has been written to partition {} of topic {} with ingestion timestamp {}.",
          result.getRecordMetadata().partition(),
          result.getRecordMetadata().topic(),
          result.getRecordMetadata().timestamp());
      }

      private void onFailure(Throwable t) {
        log.warn("Message has not been written to topic {}.", topicName, t);
      }

    @GetMapping("/kafka/messages")
    public String getMessages() {
        return ("Message sent to the Kafka Topic myTopic Successfully" + myTopicConsumer.getMessages());
    }
}