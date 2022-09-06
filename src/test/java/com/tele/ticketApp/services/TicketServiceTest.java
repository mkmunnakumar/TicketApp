package com.tele.ticketApp.services;

import com.tele.ticketApp.TicketAppApplication;
import com.tele.ticketApp.entity.Passenger;
import com.tele.ticketApp.model.Employee;
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
        Passenger passenger = getPassenger();
        given(ticketService.getPassengerByPnr(1L)).willReturn(passenger);
        Passenger result = ticketService.getPassengerByPnr(1L);
        assertEquals(result.getPnr(),1);
    }
    @Test
    @Rollback(value = false)
    public void bookTicketTest() {
        Passenger passenger = getPassenger();
        ticketService.bookTicket(passenger);
        assertThat(passenger.getPnr()).isGreaterThan(0);
    }
    @Test
    void retriveAllPassengerTest() {
        Passenger passenger = getPassenger();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        given(ticketService.retriveAllPassenger()).willReturn(passengers);
        List<Passenger> result = ticketService.retriveAllPassenger();
        assertEquals(result.size(), 1);
    }
    @Test
    public void getAllEmpTest() throws Exception{
        Employee employee = getEmployee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(ticketService.getAllEmployee()).willReturn(employees);
        List<Employee> result = ticketService.getAllEmployee();
        assertEquals(result.size(), 1);
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee employee = getEmployee();
        given(ticketService.getEmployeeById(1)).willReturn(employee);
        Employee returnEmp = ticketService.getEmployeeById(1);
        assertEquals(returnEmp.getEid(),1);
    }

    @Test
    public void addEmployeeTest(){
        Employee employee = getEmployee();
        ticketService.createEmployee(employee);
        assertThat(employee.getEid()).isEqualTo(1);
    }

    private Passenger getPassenger(){
        Passenger passenger = new Passenger();
        passenger.setPnr(1L);
        passenger.setName("Munna Kumar");
        passenger.setFare(2400.1);
        return  passenger;
    }

    private Employee getEmployee(){
        Employee employee = new Employee();
        employee.setEid(1);
        employee.setEname("Rakesh Singh");
        employee.setPosition("Doctor");
        return employee;
    }

}