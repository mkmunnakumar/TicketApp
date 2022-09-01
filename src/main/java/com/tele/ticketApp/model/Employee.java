package com.tele.ticketApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class Employee {

    private Integer eid;
    private String ename;
    private String position;
    
    
	public Employee() {
		super();
	}
	
	public Employee(Integer eid, String ename, String position) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.position = position;
	}

	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", position=" + position + "]";
	}
	
}
