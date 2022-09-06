package com.tele.ticketApp.entity;

import javax.persistence.*;

@Entity
@Table(name="ticket_table")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pnr;
    private String name;
    private Double fare;


    public Passenger() {
        super();
    }

    public Passenger(Long pnr, String name, Double fare) {
        this.pnr = pnr;
        this.name = name;
        //this.age = age;
        this.fare = fare;
    }

    public Long getPnr() {
        return pnr;
    }

    public void setPnr(Long pnr) {
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "pnr=" + pnr +
                ", name='" + name + '\'' +
                ", fare="+ fare +
                '}';
    }
}
