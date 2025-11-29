package com.Spring.PgManagement;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.Entity.Rent;
import com.Spring.PgManagement.Entity.Room;
import com.Spring.PgManagement.Repository.PgInquiryRepository;
import com.Spring.PgManagement.Repository.RentRepository;
import com.Spring.PgManagement.Repository.RoomRepository;

@Component
public class PgManagementInquirydetails {

	 @Autowired
	 RoomRepository roomRep;
	 
	 @Autowired
	 RentRepository rentRep;
	 
	   // Retrieving inquiryID 
	   @Autowired
	   PgInquiryRepository inqiryRep;
	 
	 Scanner sc = new Scanner(System.in);
	public void AvailableRoom() {
		
		// LIST OF ROOMS DISPLAY VACANIES
        List<Room> rooms = roomRep.findByAvailableBedsGreaterThan(0);
        
        System.out.println("------------- Available Rooms ------------------");
        
        System.out.println("RoomNumber\tFloor\tBeds Available");
        System.out.println("-----------------------------------------------");
        for (Room r : rooms) {
            System.out.println(r.getRoom_number() + "\t" + r.getFloor_number() + "\t" + r.getAvailableBeds());
        }
        System.out.println("---------------------------------------------------");
	  }
	
	public void RoomRent() {
		
		System.out.println("------------- Rent Details -------------");
		System.out.println("RoomNumber\tMonthlyRent\tAdvance\tFoodIncluded");
		System.out.println("-----------------------------------------------");
		
		List<Rent> rent = rentRep.findAll();
		
		for(Rent rentDetail:rent) {
			System.out.println(rentDetail.getRoom_type() + "     " + rentDetail.getMonthly_rent() + "     " +
					rentDetail.getAdvanced() + "     " + rentDetail.getFood_included());
		}
		System.out.println("-----------------------------------------------");
	}
	
	
	public void feedBack() {
		System.out.println("Would you like to share your feedback?");
		System.out.println("1. Yes, I want to give feedback ❤️");
		System.out.println("2. No, go back to Main Menu ❌");
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		sc.nextLine();
		
		if(choice == 1) {
			//System.out.println("Rooms are clean and rent is reasonable.");
			int Id = inqiryRep.inqueryId();
			
			// Update feedback
			int feedback = inqiryRep.updateFeedback("YES", Id);
			System.out.println("Thank you for your positive feedback! ❤️ added to favourites.");
		} else {
			return;
			
		}
	}
}
