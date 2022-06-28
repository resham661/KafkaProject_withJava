//package HelloWorldKafkaProject;
//
//import org.springframework.kafka.support.SendResult;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaSender {
//	
//	private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);
//    
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//	
//	String kafkaTopic = "java_in_use_topic";
//	
////	public void send(String message) {
////		
////		kafkaTemplate.send(kafkaTopic, message);
////		
////	}
//	public void send(String message) {
//		logger.info(String.format("$$$$ => Producing message: %s", message));
//		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(kafkaTopic, message);
//		future.addCallback(new ListenableFutureCallback<>() {
//			@Override
//			public void onFailure(Throwable ex) {
//				logger.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
//			}
//
//			@Override
//			public void onSuccess(SendResult<String, String> result) {
//				logger.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
//			}
//		});
//    }
//}