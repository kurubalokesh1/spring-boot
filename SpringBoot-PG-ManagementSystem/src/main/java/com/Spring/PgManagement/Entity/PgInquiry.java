package com.Spring.PgManagement.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="inquiry")
public class PgInquiry {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int inquiry_id;
	private String name;
	private String email;
	private LocalDate viewed_date;
	private String interested;
	
	public PgInquiry(String name, String email,LocalDate viewed_date) {
		this.name = name;
		this.email = email;
		this.viewed_date = viewed_date;
		
	}

	public int getInquiry_id() {
		return inquiry_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getViewed_date() {
		return viewed_date;
	}

	public String getInterested() {
		return interested;
	}
	
	
	
	
}
