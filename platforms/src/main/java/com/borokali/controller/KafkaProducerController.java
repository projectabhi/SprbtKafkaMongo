package com.borokali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {
	
	private final static String STRING_TOPIC="SimpleStringTopic2";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping("/sendStringMessage/{msg}")
	public String produceStringMessage(@PathVariable("msg") String message) {
		kafkaTemplate.send(STRING_TOPIC, message);
		
		return message+" is sent to kafka topic";
	}
}
