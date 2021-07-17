package com.borokali.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerController {
		
	/*
	 * @KafkaListener(topics = "SimpleStringTopic2" , groupId = "DEF_FRP") public
	 * void consume(String message) {
	 * log.info(String.format("$$$$ => Consumed message: %s", message)); }
	 */
	
	@KafkaListener(groupId = "DEF_FRP",topicPartitions = @TopicPartition(topic = "SimpleStringTopic2",
			partitions = {"0","1"}),containerFactory = "kafkaListenerContainerFactory")
    public void consume2(String message) {
        log.info(String.format("$$$$ => Consumed2 message: %s", message));
    }

}
