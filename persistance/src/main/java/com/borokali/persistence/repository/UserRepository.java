package com.borokali.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.borokali.persistence.entity.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String>{

}
