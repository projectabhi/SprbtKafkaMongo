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


	
	/**
	 * Fetch size was not working with mysql
	 *  when using MySQL in order to really stream the results we need to satisfy three conditions:
     *	i. Forward-only resultset - Forward-only seems to be set already by Spring Data
     *  ii. Read-only statement - @Transactional(readOnly = true)
     *  iii. Fetch-size set to Integer.MIN_VALUE

	 */
	@QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
	@Query(value = "SELECT p FROM ParkingTickets p")
    Stream<ParkingTickets> collectTickets();
//    
//    public void deleteByViolationCodeLessThan(Integer violationCode);
}                                                                                                                                                         