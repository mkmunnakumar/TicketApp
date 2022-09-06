package com.tele.ticketApp.services;

import com.tele.ticketApp.entity.Passenger;
import com.tele.ticketApp.model.Employee;
import com.tele.ticketApp.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepo repo;
    @Value("${custom.external-api.get_all_emp}")
    private String get_all_emp;
    @Value("${custom.external-api.get_emp_by_id}")
    private String get_emp_by_id;
    @Value("${custom.external-api.add_emp}")
    private String add_emp;

    public Passenger getPassengerByPnr(Long pnr){
        return repo.findById(pnr).orElse(null);
    }
    public List<Passenger> retriveAllPassenger(){
        return repo.findAll();
    }

    public Passenger bookTicket(Passenger passenger) {
        return repo.save(passenger);
    }

    //External API (Employee API) Service method calling
    public List<Employee> getAllEmployee() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(get_all_emp, Employee[].class);
        System.out.println("Status Code: " + responseEntity.getStatusCode());
        for (Employee e : responseEntity.getBody()) {
            System.out.println(e);
        }
        return List.of(responseEntity.getBody());
    }

    public Employee createEmployee(Employee employee) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> requestEntity = new HttpEntity<>(employee,headers);
        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(add_emp, requestEntity, Employee.class);
        Employee emp = responseEntity.getBody();
        return emp;
    }

    public Employee getEmployeeById(Integer eid) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(get_emp_by_id, Employee.class,eid);
        System.out.println("Status Code: " + responseEntity.getStatusCode());
        Employee emp = responseEntity.getBody();
        System.out.println(emp);
        return emp;
    }


}

