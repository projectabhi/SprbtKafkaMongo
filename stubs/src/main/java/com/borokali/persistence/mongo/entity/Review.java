package com.borokali.persistence.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	private String userName;
	private int rating;
	private boolean approved;
}
