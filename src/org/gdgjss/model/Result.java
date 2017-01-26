package org.gdgjss.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;
@Repository
@Entity
public class Result {
	@Id
	private String rollno;
	private String contact;
	private String name;
	private int netMarks;
	private int rgtAns;
	private int wngAns;
	private int notAns;
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNetMarks() {
		return netMarks;
	}
	public void setNetMarks(int netMarks) {
		this.netMarks = netMarks;
	}
	public int getRgtAns() {
		return rgtAns;
	}
	public void setRgtAns(int rgtAns) {
		this.rgtAns = rgtAns;
	}
	public int getWngAns() {
		return wngAns;
	}
	public void setWngAns(int wngAns) {
		this.wngAns = wngAns;
	}
	public int getNotAns() {
		return notAns;
	}
	public void setNotAns(int notAns) {
		this.notAns = notAns;
	}
	
}
