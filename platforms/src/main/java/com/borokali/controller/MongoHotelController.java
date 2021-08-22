package com.borokali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borokali.persistence.mongo.entity.Hotel;
import com.borokali.persistence.mongo.entity.QHotel;
import com.borokali.persistence.mongo.repository.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotels")
@Slf4j
public class MongoHotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("/getAllHotels")
	public List<Hotel> getAllHotels(){
		List<Hotel> hotels=this.hotelRepository.findAll();
		
		return hotels;
	}
	
	@PutMapping
	public void insert(@RequestBody Hotel hotel) {
		this.hotelRepository.insert(hotel);
	}
	
	@PostMapping
	public void update(@RequestBody Hotel hotel) {
		this.hotelRepository.save(hotel);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.hotelRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Hotel getById(@PathVariable("id")String id) {
		log.info("Filtering data by mongorepo default findById...");
		Hotel hotel=this.hotelRepository.findById(id).get();
		return hotel;
	}
	
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getByPricePerNight(@PathVariable("maxPrice")int  maxPrice) {
		log.info("Filtering data by mongorepo findByPricePerNightLessThan...");
		List<Hotel> hotels=this.hotelRepository.findByPricePerNightLessThan(maxPrice);
		return hotels;
	}
	
	@GetMapping("/address/{city}")
	public List<Hotel> getByCity(@PathVariable("city")String city) {
		log.info("Filtering data by city mongo query language...");
		List<Hotel> hotels=this.hotelRepository.findByCity(city);
		return hotels;
	}
	
	@GetMapping("/country/{country}")
	public List<Hotel> getByCountry(@PathVariable("country")String country) {
		log.info("Filtering data by Country using query dsl library...");
		QHotel qHotel=new QHotel("hotel");
		BooleanExpression filterCondition=qHotel.address.country.eq(country);
		List<Hotel> hotels=(List<Hotel>) this.hotelRepository.findAll(filterCondition);
		
		return hotels;
	}
	
	@GetMapping("/recommend")
	public List<Hotel> getRecommended() {
		final int maxPrice=1000;
		final int minRating=7;
		
		log.info("Filtering data by multiple conditions using query dsl library...");
		
		QHotel qHotel=new QHotel("hotel");
		BooleanExpression filterByPrice=qHotel.pricePerNight.lt(maxPrice);
		BooleanExpression filterByRating=qHotel.reviews.any().rating.gt(minRating);
		
		List<Hotel> hotels=(List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));
		
		return hotels;
	}
}
