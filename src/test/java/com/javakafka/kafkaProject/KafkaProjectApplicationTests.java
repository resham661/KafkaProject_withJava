package com.javakafka.kafkaProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.javakafka.kafkaProject.consumer.MyTopicConsumer;
import com.javakafka.kafkaProject.controller.KafkaController;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {KafkaProjectApplicationTests.topic})
class KafkaProjectApplicationTests {

	@Test
	void contextLoads() {
	}
	
    @Autowired
	KafkaController producer;
	
	@Autowired
    private MyTopicConsumer consumer;

    static final String topic = "myTopic";

    @Test
	public void testReceive() throws Exception {
    	String message = "Hello Kafka!";
	    (producer).produce(message);
	    consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
	    assertThat(consumer.getLatch().getCount()).isEqualTo(1);  
	 //   assertThat(producer.getMessages()!=null);
	  }
}