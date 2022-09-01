package com.tele.ticketApp.entity;

import com.tele.ticketApp.repository.TicketRepo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PassengerTest {
    @Autowired
    private TicketRepo ticketRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void bookTicketTest(){
        Passenger passenger = new Passenger(1L,"Munna Kumar",28,2702.4);

        ticketRepo.save(passenger);
        assertThat(passenger.getPnr()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getPassengerByPnr(){
        Passenger passenger = ticketRepo.findById(1L).get();
        assertThat(passenger.getPnr()).isEqualTo(1);
    }
    @Test
    @Order(3)
    public void getAllPassenger(){
        List<Passenger> employee = ticketRepo.findAll();
        assertThat(employee.size()).isGreaterThan(0);
    }

}