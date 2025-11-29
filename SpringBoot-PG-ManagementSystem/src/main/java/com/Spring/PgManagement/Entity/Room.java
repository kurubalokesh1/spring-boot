package com.Spring.PgManagement.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Room {

	@Id
	@Column(name="room_number")
	private String roomNumber;
	private String room_type;
	private int floor_number;
	private int capacity;
	private int occupied_count;
	 
	@Column(name = "available_beds")
	private int availableBeds;
	
	// FOREIGN KEY OF ONETOMANY
	@ManyToOne
	@JoinColumn(name="pg_id")
	Admin admin;
	
	// CONSTRUCTORS TO INJECT INSTANCES VALUES
	public Room() {
		
	}

	public Room(String roomNumber, String room_type, int floor_number, int capacity, int occupied_count,
			int available_beds, Admin admin) {
		this.roomNumber = roomNumber;
		this.room_type = room_type;
		this.floor_number = floor_number;
		this.capacity = capacity;
		this.occupied_count = occupied_count;
		this.availableBeds = available_beds;
		this.admin = admin;
	}

	public String getRoom_number() {
		return roomNumber;
	}

	public String getRoom_type() {
		return room_type;
	}

	public int getFloor_number() {
		return floor_number;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getOccupied_count() {
		return occupied_count;
	}

	 public int getAvailableBeds() {
	        return availableBeds;
	    }

	public Admin getAdmin() {
		return admin;
	}

	
	
	
}
