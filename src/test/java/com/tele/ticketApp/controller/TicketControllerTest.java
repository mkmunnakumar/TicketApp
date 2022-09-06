package com.tele.ticketApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tele.ticketApp.entity.Passenger;
import com.tele.ticketApp.model.Employee;
import com.tele.ticketApp.repository.TicketRepo;
import com.tele.ticketApp.services.TicketService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TicketService ticketService;

     ObjectMapper objectMapper = new ObjectMapper();
     ObjectWriter objectWriter = objectMapper.writer();

     @Mock
    private TicketRepo ticketRepo;
   @InjectMocks
   @Autowired
    private TicketController ticketController;

   @Before
   public void setUp(){
       MockitoAnnotations.initMocks(this);
       this.mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
   }

    @Test
    public void bookTicketTest() throws Exception{
       Passenger passenger = getPassenger();
       when(ticketRepo.save(passenger)).thenReturn(passenger);
       String content = objectWriter.writeValueAsString(passenger);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/bookticket")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void getPassengerByPnr() throws Exception{
       Passenger passenger = getPassenger();
       when(ticketRepo.findById(passenger.getPnr())).thenReturn(java.util.Optional.of(passenger));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/passenger/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllPassengerTest() throws Exception {
        Passenger passenger = getPassenger();
        List<Passenger> passengers = new ArrayList<>();
        Mockito.when(ticketRepo.findAll()).thenReturn(passengers);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/passenger")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addEmpTest(){
        Employee employee = getEmployee();
        ticketController.addEmp(employee);
        assertThat(employee.getEid()).isGreaterThan(0);
    }
    @Test
    void getAllEmpTest() throws Exception {
        Employee employee = getEmployee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(ticketController.getAllEmployee()).willReturn(employees);
        List<Employee> result = (List<Employee>) ticketController.getAllEmployee();
        assertEquals(result.size(), 1);
    }
    @Test
    void getEmpByIdTest() {
        Employee employees = getEmployee();
        given(ticketController.getEmployeeById(1)).willReturn(employees);
        Employee result = ticketController.getEmployeeById(1);
        assertEquals(result.getEid(),1);
    }

    private Passenger getPassenger() {
        Passenger passenger = new Passenger();
        passenger.setPnr(1L);
        passenger.setName("Ruchi");
        passenger.setFare(2702.7);
        return passenger;
    }

    private Employee getEmployee(){
        Employee employee = new Employee();
        employee.setEid(1);
        employee.setEname("Munna Kumar");
        employee.setPosition("Doctor");
        return employee;
    }




}
