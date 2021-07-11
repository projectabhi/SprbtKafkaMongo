package com.borokali.services.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerService {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private final static String STRING_TOPIC="SimpleStringTopic2";

	public void sendMessage(String message) {
		log.info(String.format("$$$$ => Producing message: %s", message));
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(STRING_TOPIC, "111", message);
		record.headers().add(new RecordHeader("myheader", "record_created".getBytes()));

		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(record);
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
			}
		});
	}

}
