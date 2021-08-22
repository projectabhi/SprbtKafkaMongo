package com.borokali.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.borokali.persistence.mongo.entity.Address;
import com.borokali.persistence.mongo.entity.Hotel;
import com.borokali.persistence.mongo.entity.Review;
import com.borokali.persistence.mongo.entity.Users;
import com.borokali.persistence.mongo.repository.HotelRepository;
import com.borokali.persistence.mongo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class MongodbSeeder implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info("Persisting users collection .... ");
		Users user = new Users("111", "boro", "boro");
		Users user2 = new Users("112", "abhi", "abhi");
		List<Users> listUser = Arrays.asList(user, user2);

		log.info("Deleting users...");
		userRepository.deleteAll();

		userRepository.saveAll(listUser);
		log.info("User persist done .... ");
		
		persistHotels();
	}

	public void persistHotels() {
		Hotel marriot = new Hotel(null, "Marriot", 1000, new Address("Paris", "France"),
				Arrays.asList(new Review("john", 9, false), new Review("mary", 7, true)));

		Hotel ibis = new Hotel(null, "Ibis", 900, new Address("Bucharest", "Romania"),
				Arrays.asList(new Review("teddy", 9, true)));

		Hotel ethnotel = new Hotel(null, "Ethnotel", 2000, new Address("Rome", "Italy"),
				new ArrayList<>());
		
		List<Hotel> hotels=Arrays.asList(marriot,ibis,ethnotel);
		
		log.info("Deleting Hotels...");
		this.hotelRepository.deleteAll();
		
		log.info("Persisting into Hotels collection...");
		this.hotelRepository.saveAll(hotels);
	}

}
