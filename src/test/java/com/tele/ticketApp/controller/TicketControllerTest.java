package com.tele.ticketApp.controller;

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
class TicketControllerTest {

    @Autowired
    @MockBean
    private TicketController ticketController;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void addEmpTest(){
        Passenger passenger = getPassenger();
        ticketController.bookTicket(passenger);
        assertThat(passenger.getPnr()).isGreaterThan(0);
    }

    @Test
    void getPassengerByPnr() {
        Passenger passenger = getPassenger();
        given(ticketController.getPassengerByPnr(1L)).willReturn(passenger);
        Passenger result = ticketController.getPassengerByPnr(1L);
        assertEquals(result.getPnr(),1);
    }
    @Test
    void getAllEmpTest() {
        Passenger passenger = getPassenger();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        given(ticketController.getPassenger()).willReturn(passengers);
        List<Passenger> result = ticketController.getPassenger();
        assertEquals(result.size(), 1);
    }

    private Passenger getPassenger() {
        Passenger passenger = new Passenger();
        passenger.setPnr(1L);
        passenger.setName("Ruchi");
        passenger.setFare(2702.7);
        return passenger;
    }


}