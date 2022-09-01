package com.tele.ticketApp.repository;

import com.tele.ticketApp.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TicketRepo extends JpaRepository<Passenger, Long> {
}
