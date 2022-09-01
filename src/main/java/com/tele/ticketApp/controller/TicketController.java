package com.tele.ticketApp.controller;

import com.tele.ticketApp.entity.Passenger;
import com.tele.ticketApp.model.Employee;
import com.tele.ticketApp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void getAllEmployee() throws Exception {
        String get_All_Emp_URL = "http://localhost:8081/emp";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(get_All_Emp_URL, Employee[].class);
        System.out.println("Status Code: " + responseEntity.getStatusCode());
        for (Employee e : responseEntity.getBody()) {
            System.out.println(e);
        }
    }
    @RequestMapping("/employee/{eid}")
    public void getEmployeeById() {
        String url = "http://localhost:8081/employee/{eid}";

        Integer empId= 1;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class,empId);

        System.out.println("Status Code: " + responseEntity.getStatusCode());
        Employee emp = responseEntity.getBody();
        System.out.println(emp);
    }

    @RequestMapping("/addEmp")
    public void addEmployee(){
        String url = "http://localhost:8081/addEmp";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Employee empl = new Employee();
        empl.setEname("Rakesh Maria");
        empl.setPosition("Doctor");
        HttpEntity<Employee> requestEntity = new HttpEntity<>(empl,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(url, requestEntity, Employee.class);
        System.out.println("Status code: "+ responseEntity.getStatusCode());
        Employee emp = responseEntity.getBody();
        System.out.println(emp);
    }

}
