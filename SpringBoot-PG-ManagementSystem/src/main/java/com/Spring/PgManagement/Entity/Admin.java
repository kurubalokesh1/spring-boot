package com.Spring.PgManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin {

	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private int PgId;
	    private String pgName;
	    private String Location;
	    private String OwnerName;

	    public Admin() {
	        // default constructor required by JPA
	    }

	    public Admin(String pgName, String Location, String OwnerName) {
	        this.pgName = pgName;
	        this.Location = Location;
	        this.OwnerName = OwnerName;
	    }

		public int getPgId() {
			return PgId;
		}

		public void setPgId(int pgId) {
			PgId = pgId;
		}
	    
	    
}

