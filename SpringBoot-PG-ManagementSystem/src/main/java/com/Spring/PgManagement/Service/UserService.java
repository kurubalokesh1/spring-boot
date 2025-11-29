package com.Spring.PgManagement.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.Entity.Admin;
import com.Spring.PgManagement.Entity.RentDetails;
import com.Spring.PgManagement.Entity.Room;
import com.Spring.PgManagement.Entity.User;
import com.Spring.PgManagement.Repository.AdminRepository;
import com.Spring.PgManagement.Repository.PaymentRepository;
import com.Spring.PgManagement.Repository.RoomRepository;
import com.Spring.PgManagement.Repository.UserRepository;

@Component
public class UserService {

    @Autowired
    RoomRepository roomRep;
    
    @Autowired
    AdminRepository AdminRep;
    
    @Autowired
    UserRepository userRep;
    
    @Autowired
	PaymentRepository paymentRep;

    @Autowired
    JavaMailSender mailSender;  // EMAIL SENDER CLASS
    
    Scanner sc = new Scanner(System.in);

	//private String roomNumber;

    // ✅ now a normal method (not ApplicationRunner)
    public void registerUser() {

    	// LIST OF ROOMS DISPLAY VACANIES
        List<Room> rooms = roomRep.findByAvailableBedsGreaterThan(0);
        
     // VERIFY WHETHER ROOM BEDS ARE VACANIES ARE NOT
        System.out.println("------------- Available Rooms ------------------");
        if (rooms.isEmpty()) {
        	  System.out.println("Rooms are full, choose another room!");
        } else {
        
        System.out.println("RoomNumber\tFloor\tBeds Available");
        System.out.println("-----------------------------------------------");
        for (Room r : rooms) {
            System.out.println(r.getRoom_number() + "\t" + r.getFloor_number() + "\t" + r.getAvailableBeds());
        }
        System.out.println("---------------------------------------------------");
        // ENTER USER DETAILS INTO RECORDS
        
        System.out.print("Enter New User Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        long ph = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();        

        // USER CHOOSE ROOM BASED ON FACILITIES
        System.out.print("Enter Room Number to Book: ");
        String roomNumber = sc.nextLine();
        
        // PG ID
         Admin admin = AdminRep.findByPgName("Sunrise PG");
         
         // INSERT USER DETAILS IN A TABLE
         User user = new User(name,ph,email,gender,roomNumber,LocalDate.now(),admin);
         
         // SEND EMAIL FOR USER
         int otp;
         Random random = new Random();
         otp = random.nextInt(1000, 9999);
         
         // CREATE OBJECT OF THE MAIL MESSAGE
         SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom("Sunrise PG <lokeshkuruba18@gmail.com>");
         message.setTo(email);
         message.setSubject("Registration Sunrise PG 🏢");
         message.setText("Welcome to Sunrise PG!\n\n" +
         "Your OTP for verification is: " + otp + "\n\n" +
         "Thank you for choosing our PG 😊");
         try {
        	 mailSender.send(message);
        	 System.out.println("✔ Email Sent Successfully!");        	       	                          
         } catch (Exception e) {
         	System.out.println("❌ Failed to send email. Invalid Email ID or network issue.");
         }

         // VERIFY OTP
         boolean reEnter = true;
         while(reEnter) {
         System.out.print("Enter the Received OTP:");
         int received_OTP = sc.nextInt();
         
        	 if(otp == received_OTP) {
        		 userRep.save(user);
        		 System.out.println("✔ OTP Verified. User Inserted Successfully!");	
        		 
        		 message.setSubject("Welcome to Sunrise PG 🏢");
        		 message.setText(name + " 🏠 Your room number is " + roomNumber + " has been booked successfully");
        		 mailSender.send(message);
        		 reEnter = false;
        	 } else {     
        	 System.out.println("❌ Entered Wrong OTP. Try Again!");       
           }
         }
         
        // Logic to assign the user to the selected room can go here
        System.out.println("✅ User " + name + " registered successfully for Room " + roomNumber); 
         
        
        // UPDATE ROOM IF RECORD INSERTED
        int room = roomRep.updateRoom(roomNumber);
    	System.out.println(room + " ROW UPDATED");
    	
    	 // WHENEVER NEW USER ARE INSERT UPDATE RENTDETAILS TABLE
        RentDetails rent = new RentDetails(roomNumber,name,ph,admin);
        paymentRep.save(rent);
        
        }               
    }
    
    // DELETE USER FROM TABLE
    public void deleteUser() {
    	
    	System.out.print("Enter the user ID:");
    	int id = sc.nextInt();
    	sc.nextLine();
    	
    	System.out.print("Enter the Room Number:");
    	String room = sc.nextLine();
    	
    	// DELETE USER FROM TABLE
    	int row = userRep.deleteByUserIdAndRoomNumber(id, room);
    	System.out.println(row + " ROW DELETED SUCCESSFULLY!");
    	
    	// UPDATE ROOM IF USER DELETED RECORD
    	int deletedRowUpdate = roomRep.deleteRoom(room);
    	System.out.println(deletedRowUpdate + " ROW UPDATED SUCCESSFULLY!");
    }
    
    // VIEW ALL USER
    public void viewAllUser() {
    	List<User> allUser = userRep.findAll();
    	
    	System.out.println("---------------- ALL USER ---------------");
    	for(User user: allUser) {
    		System.out.println("Room Number: " + user.getRoom_number());
    		System.out.println("Name: " + user.getName());
    		System.out.println("Phone Number: " + user.getPhone_number());
    		System.out.println("PG ID: " + user.getUserId());
    		System.out.println();
    	}
    	System.out.println("----------------------------");
    }
}
