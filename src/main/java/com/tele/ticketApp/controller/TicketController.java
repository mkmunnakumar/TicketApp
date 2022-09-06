package com.tele.ticketApp.controller;

import com.tele.ticketApp.entity.Passenger;
import com.tele.ticketApp.model.Employee;
import com.tele.ticketApp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService service;

    @GetMapping("/passenger/{pnr}")
    public Passenger getPassengerByPnr(@PathVariable Long pnr){
        return service.getPassengerByPnr(pnr);
    }
    @GetMapping("/passenger")
    public List<Passenger> getPassenger(){
        return service.retriveAllPassenger();
    }
    @PostMapping("/bookticket")
    public Passenger bookTicket(@RequestBody Passenger passenger) {

        return service.bookTicket(passenger);
    }

    //RestTemplet

    @RequestMapping("emp")
    public List<Employee> getAllEmployee() throws Exception {
        return service.getAllEmployee();
    }
    @PostMapping("/addEmp")
    public Employee addEmp(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }
    @GetMapping("/employee/{eid}")
    public Employee getEmployeeById(@PathVariable Integer eid){
        return service.getEmployeeById(eid);
    }


}
