package org.gdgjss.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

/**
 * Entity set: Registration of participation.
 * 
 * @author Tilhari
 *
 */
@Repository
@Entity
public class Registration {

	private String name;
	private String college;
	private String branch;
	@Id
	private String rollno;
	private String contact;
	private String email;
	private String pass;
	private boolean attempt;
	
	
	public boolean isAttempt() {
		return attempt;
	}
	public void setAttempt(boolean attempt) {
		this.attempt = attempt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	
}