package com.borokali.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.borokali.persistence.entity.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {
	List<Hotel> findByPricePerNightLessThan(int maxPrice);

	@Query(value = "{\"address.city\":?0}")
	List<Hotel> findByCity(String city);
}
