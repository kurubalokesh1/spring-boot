package com.Spring.PgManagement;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.Entity.Admin;
import com.Spring.PgManagement.Exception.UnauthorizedException;
import com.Spring.PgManagement.Repository.AdminRepository;
import com.Spring.PgManagement.Repository.RoomRepository;

import com.Spring.PgManagement.Service.PaymentReportService;
import com.Spring.PgManagement.Service.PaymentService;
import com.Spring.PgManagement.Service.PgInquiryService;
import com.Spring.PgManagement.Service.RoomService;
import com.Spring.PgManagement.Service.UserService;

@Component
public class PgManagementConsoleApp implements ApplicationRunner {

    @Autowired
    private AdminRepository adminRep;

    @Autowired
    private RoomRepository roomRep;

    @Autowired
    private UserService registerUser; // ✅ injecting user registration helper

    @Autowired
    private RoomService roomAccess; // OBJECT OF A RoomUpdateAccess TO UPDATE ROOM
    
    @Autowired
    PaymentService paymentRep; 
    
    @Autowired
    PaymentReportService paymentReport;
    
    @Autowired
    PgInquiryService pgInquiry;
    
    Scanner sc = new Scanner(System.in);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            System.out.println("------------------ PG MANAGEMENT SYSTEM -----------------------");
            System.out.println("1. Inquiry/Register");
            System.out.println("2. Admin Menu");                                                                       
            System.out.println("3. Room Menu");                                                                        
            System.out.println("4. Make payment");
            System.out.println("5. View Payment Report");
            System.out.println("6. Exit");
            System.out.print("Enter the Option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
            case 1:
            	pgInquiry.startInquiry();
            	break;
                case 2:
                    adminMenu();
                    
                    break;
                case 3:
                	showRoomMenu();
                    break;
                case 4:                	
                	paymentRep.processPayment();
                    break;
                case 5:
                	paymentReport.viewReport();
                	break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ✅ Admin menu logic
    public void adminMenu() {
        while (true) {
            System.out.println("----------- Admin Menu ---------------");
            System.out.println("1. Add Admin");
            System.out.println("2. User Registration");
            System.out.println("3. Delete user");
            System.out.println("4. View All User");
            System.out.println("5. Exiting....");
            System.out.print("Enter the choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                	registerUser.registerUser(); ;
                	break;
                case 3:
                	registerUser.deleteUser();
                case 4:
                	registerUser.viewAllUser();
                	break;
                case 5:
                	System.out.println("Exiting......."); 
                	
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ✅ Add admin
    public void addAdmin() {
        System.out.print("Enter PG Name: ");
        String pgName = sc.nextLine();
        System.out.print("Enter Location: ");
        String loc = sc.nextLine();
        System.out.print("Enter Owner Name: ");
        String owner = sc.nextLine();

        Admin admin = new Admin(pgName, loc, owner);
        adminRep.save(admin);
        System.out.println("✅ Admin added successfully!");
    }

    // ✅ Add room
    public void showRoomMenu() {
    	 boolean admin = false;
    	 while (true) {
             System.out.println("----------- Room Menu ---------------");
             System.out.println("1. Admin Acess");
             System.out.println("2. Add Room");
             System.out.println("3. Update Room");
             System.out.println("4. Delete Room");
             System.out.println("5. Exiting....");
             System.out.print("Enter the choice: ");
             int ch = sc.nextInt();
             sc.nextLine();
             
             switch(ch) {
             
             case 1:
            	 try {
					roomAccess.adminAccess();
					admin = true;
				} catch (UnauthorizedException e) {
					e.printStackTrace();
				}
            	 break;
             case 2:
            	 
            	 if (!admin) {
            		 System.out.println("❌ You don’t have permission. Admin must authorize first.");
            	 } else {
            		 roomAccess.AddRoom();
            	 }
            	 break;
            	 
                 case 3:
                	 
                	 if (!admin) {
                		 System.out.println("❌ You don’t have permission. Admin must authorize first.");
                	 } else {
                	 roomAccess.updateRoom();
                	 }
            	 break;
//            	 
             case 4:
            	 
            	 if (!admin) {
            		 System.out.println("❌ You don’t have permission. Admin must authorize first.");
            	 } else {
            	 roomAccess.deleteRoom();
            	 }
            	 break;
            	 
             case 5:
            	 System.out.println("Exiting..........");
            	 
             } 
             
    	   }
    	 }   
}
