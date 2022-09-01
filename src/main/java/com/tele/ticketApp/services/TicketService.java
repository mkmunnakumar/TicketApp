package com.tele.ticketApp.services;

import com.tele.ticketApp.entity.Passenger;
import com.tele.ticketApp.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepo repo;
    public Passenger getPassengerByPnr(Long pnr){
        return repo.findById(pnr).orElse(null);
    }
    public List<Passenger> retriveAllPassenger(){
        return repo.findAll();
    }

    public Passenger bookTicket(Passenger passenger) {
        return repo.save(passenger);
    }

}

