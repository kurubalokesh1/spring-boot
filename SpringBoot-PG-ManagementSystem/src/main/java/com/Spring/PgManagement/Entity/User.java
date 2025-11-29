package com.Spring.PgManagement.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	private String name;
	private long phone_number;
	private String email;
	private String gender;
	private String room_number;
	private LocalDate register_date;
	
	// FOREIGN KEY MANYTOONE
	@ManyToOne
	@JoinColumn(name="pg_id")
	Admin admin;

	public User() {
		
	}
	
	public User(String name, long phone_number, String email, String gender, String room_number,
			LocalDate register_date, Admin admin) {
		this.name = name;
		this.phone_number = phone_number;
		this.email = email;
		this.gender = gender;
		this.room_number = room_number;
		this.register_date = register_date;
		this.admin  =  admin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRoom_number() {
		return room_number;
	}

	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}

	public LocalDate getRegister_date() {
		return register_date;
	}

	public void setRegister_date(LocalDate register_date) {
		this.register_date = register_date;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
