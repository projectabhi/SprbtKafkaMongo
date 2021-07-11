package com.borokali.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerController {
		
	@KafkaListener(topics = "SimpleStringTopic2"/* , groupId = "group_id" */)
    public void consume(String message) {
        log.info(String.format("$$$$ => Consumed message: %s", message));
    }

}
