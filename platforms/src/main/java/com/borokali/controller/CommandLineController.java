package com.borokali.controller;

import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.borokali.persistence.mysql.entity.ParkingTickets;
import com.borokali.persistence.mysql.repository.TicketRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineController implements CommandLineRunner {

	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private EntityManager mysqlEntityManager;

	@Override
	@Transactional(readOnly = true)
	public void run(String... args) throws Exception {
		log.info("Running console application");

		try (Stream<ParkingTickets> tickets = ticketRepo.collectTickets()) {
			tickets.forEach(item -> {
				log.info(item.getHouseNumber());
				mysqlEntityManager.detach(item);
			});
		}

	}

}
