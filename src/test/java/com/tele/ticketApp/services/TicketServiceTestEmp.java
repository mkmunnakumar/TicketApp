package com.tele.ticketApp.services;

import com.tele.ticketApp.TicketAppApplication;
import com.tele.ticketApp.model.Employee;
import com.tele.ticketApp.repository.TicketRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TicketServiceTestEmp {
    @Autowired
    private TicketRepo ticketRepo;
    @Mock
    private TicketService ticketService;

    @Test
    public void getAllEmpTest() throws Exception{
       Employee employee = getEmployee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(ticketService.getAllEmployee()).willReturn(employees);
        List<Employee> result = ticketService.getAllEmployee();
        assertEquals(result.size(), 1);
    }

    private Employee getEmployee(){
        Employee employee = new Employee();
        employee.setEid(1);
        employee.setEname("Raman");
        employee.setPosition("Doctor");
        return employee;
    }

}