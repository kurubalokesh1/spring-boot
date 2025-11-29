package com.Spring.PgManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rent")
public class Rent {

	@Id
	private String room_type;
	private int monthly_rent;
	private int advanced;
	private String food_included;
	
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public int getMonthly_rent() {
		return monthly_rent;
	}
	public void setMonthly_rent(int monthly_rent) {
		this.monthly_rent = monthly_rent;
	}
	public int getAdvanced() {
		return advanced;
	}
	public void setAdvanced(int advanced) {
		this.advanced = advanced;
	}
	public String getFood_included() {
		return food_included;
	}
	public void setFood_included(String food_included) {
		this.food_included = food_included;
	}
	
	
}
