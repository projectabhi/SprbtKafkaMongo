package com.borokali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.borokali.services.kafka.ProducerService;

@RestController
public class KafkaProducerController {
	
	@Autowired
	private ProducerService producerService;	

	@GetMapping("/sendStringMessage/{msg}")
	public String produceStringMessage(@PathVariable("msg") String message) {

		producerService.sendMessage(message);
		
		return message+" is sent to kafka topic";
	}
}
