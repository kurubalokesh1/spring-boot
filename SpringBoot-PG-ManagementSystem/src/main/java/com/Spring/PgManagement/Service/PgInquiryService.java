package com.Spring.PgManagement.Service;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.PgManagementInquirydetails;
import com.Spring.PgManagement.Entity.PgInquiry;
import com.Spring.PgManagement.Repository.PgInquiryRepository;
import com.Spring.PgManagement.Repository.RentRepository;

@Component
public class PgInquiryService  {

	@Autowired
	PgInquiryRepository inqiryRep;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	PgManagementInquirydetails inquiryDetail;
	
	@Autowired
	UserService userRegister; // User registration by booking room
	
	@Autowired
	RentRepository rentRep; // user paid payment update rent details
	
	Scanner sc = new Scanner(System.in);
 
	public void startInquiry() {		
		 System.out.println("--------------- PG Inquiry ---------------");

		System.out.print("Enter Register name:");
	    String  name = sc.nextLine();
		System.out.print("Enter Email ID:");
		String email = sc.nextLine();
		
		int count = inqiryRep.userDetail(name,email);
		
		if(count > 0) {
			System.out.println("✔ You are already registered!");
			return;
			
		} else {
			 // USER NOT EXISTED
	        System.out.println("⚠ You are not registered! Please verify to continue.");

	     // SEND OTP TO REGISTER EMAIL
			int otp;
			Random random = new Random();
			otp = random.nextInt(1000, 9999);
			
		// SEND OTP TO REGISTER EMAIL 
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("Sunrise PG <lokeshKuruba18@gmail.com>");
		message.setTo(email);
		message.setSubject("Register Sunrise PG 🏢");
		message.setText("Dear " + name + ",\n\nYour OTP to register for Sunrise PG is: " + otp + "\n\nThank you");
		mailSender.send(message);
		
		// OTP VERIFICATION
		boolean verified = true;
		while(verified) {
			System.out.print("Enter OTP:");
			int userOtp  = sc.nextInt();
			
			if(otp == userOtp ) {
				verified = false;
                System.out.println("✔ OTP Verified Successfully!");
                
                // Save to DB
				PgInquiry inquiry = new PgInquiry(name,email,LocalDate.now());
				inqiryRep.save(inquiry);
				
				// Send Welcome Email
                message.setSubject("Welcome to Sunrise PG!");
                message.setText("Dear " + name +
                        ",\n\nYour registration is successful. You can now view PG rooms and facilities😊.");

                mailSender.send(message);
				
			} else {
				System.out.println("❌ Invalid OTP. Try again!");
			}
		 }
		}
		
		while(true) {
		System.out.println("-------------- PG Details -----------------");
		System.out.println("1. view Available Room");
		System.out.println("2. view Room Rent");
		System.out.println("3. Book a Room");
		System.out.println("4.Feedback");
		
		System.out.print("Enter the choice:");
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice) {
		 
		 case 1:
			 inquiryDetail.AvailableRoom();
			 break;
		 case 2:
			 inquiryDetail.RoomRent();
			 break;
		 case 3:
			 inquiryDetail.feedBack();
			 break;
		 case 4:
			 userRegister.registerUser();
			 SimpleMailMessage msg = new SimpleMailMessage();
			 msg.setFrom(email);
			 msg.setTo("lokeshkuruba18@gmail.com");
			 msg.setSubject("Payment");
			 msg.setText("Payment paid by:" + name + " and booked room.");
			 
			 try {
				 mailSender.send(msg);
				 System.out.println("Email sent Successfully!");
				 
				 // update rent details where payment successfully
				 System.out.print("Enter the rent amount:");
				 double rent = sc.nextDouble();
				 
				 int paidRent = rentRep.payment(rent, "Paid", email);
				 System.out.println("Payment rent details updated successfully!");
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
			 break;
		 case 5:
			 System.out.println("Exiting.....");
			 
		}
	  }
	}
}

  
