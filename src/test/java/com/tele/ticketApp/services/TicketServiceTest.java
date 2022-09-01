package com.tele.ticketApp.services;

import com.tele.ticketApp.TicketAppApplication;
import com.tele.ticketApp.entity.Passenger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = TicketAppApplication.class)
class TicketServiceTest {

    @Autowired
    @MockBean
    private TicketService ticketService;

    @Test
    public void getPassengerByPnrTest() {
        Passenger passenger = getPasenger();
        given(ticketService.getPassengerByPnr(1L)).willReturn(passenger);
        Passenger result = ticketService.getPassengerByPnr(1L);
        assertEquals(result.getPnr(),1);
    }



    @Test
    @Order(1)
    @Rollback(value = false)
    public void bookTicketTest() {
        Passenger passenger = getPassenger();
        ticketService.bookTicket(passenger);
        assertThat(passenger.getPnr()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public Passenger getPasenger() {
        Passenger passenger = new Passenger();
        passenger.setPnr(1L);
        passenger.setName("Munna Kumar");
        passenger.setFare(2700.2);
        return passenger;
    }

    @Test
    @Order(3)
    void retriveAllPassengerTest() {
        Passenger passenger = getPasenger();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        given(ticketService.retriveAllPassenger()).willReturn(passengers);
        List<Passenger> result = ticketService.retriveAllPassenger();
        assertEquals(result.size(), 1);
    }

    private Passenger getPassenger(){
        Passenger passenger = new Passenger();
        passenger.setPnr(1L);
        passenger.setName("Munna Kumar");
        passenger.setFare(2400.1);
        return  passenger;
    }

}