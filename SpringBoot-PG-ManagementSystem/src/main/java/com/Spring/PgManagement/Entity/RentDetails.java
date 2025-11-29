package com.Spring.PgManagement.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="rent_details")
public class RentDetails {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int rentId;

	    @Column(name = "room_number")
	    private String roomNumber;
	    private String name;
	    @Column(name = "phone_number")
	    private long phoneNumber;
	    private double rent;
	    @Column(name = "rent_date", nullable = false)
	    private LocalDate rentDate =  LocalDate.now();
	    
	    @Column(name="advanced")
	    private double advanced;
	    private String status = "unPaid";
	    
	    // ENTITY RELATIONSHIP
	    @ManyToOne
	    @JoinColumn(name="pg_id")
	    Admin admin;
	    
	    // CONSTRUCTOR OF A ENTITY CLASS
	    public RentDetails() {
	    	
	    }
	    //RentDetails rent = new RentDetails(roomNumber,name,ph,advanced_amount,admin);
		public RentDetails(String roomNumber, String name, long phoneNumber,
			Admin admin) {
			
			this.roomNumber = roomNumber;
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.admin = admin;
			
		}
		public int getRentId() {
			return rentId;
		}
		public String getRoomNumber() {
			return roomNumber;
		}
		public String getName() {
			return name;
		}
		public long getPhoneNumber() {
			return phoneNumber;
		}
		public double getRent() {
			return rent;
		}
		public LocalDate getRentDate() {
			return rentDate;
		}
		public double getAdvanced() {
			return advanced;
		}
		public Admin getAdmin() {
			return admin;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
	    
	    
}
