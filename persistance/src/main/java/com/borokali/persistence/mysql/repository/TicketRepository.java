package com.borokali.persistence.mysql.repository;

import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.borokali.persistence.mysql.entity.ParkingTickets;

@Repository
public interface TicketRepository extends CrudRepository<ParkingTickets, Long> {


	@QueryHints(value = {
			@QueryHint(name =org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE,value = "100")
	})
	@Query(value = "SELECT p FROM ParkingTickets p")
    Stream<ParkingTickets> collectTickets();
//    
//    public void deleteByViolationCodeLessThan(Integer violationCode);
}                                                                                                                                                         