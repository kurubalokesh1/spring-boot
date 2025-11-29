package com.Spring.PgManagement.Service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.Entity.RentDetails;
import com.Spring.PgManagement.Repository.PaymentRepository;

@Component
public class PaymentReportService {

	@Autowired
	PaymentRepository paymentRep;
	
	public void viewReport() {
		Scanner sc = new Scanner(System.in);
		System.out.println("------ Payment Report ------");
        System.out.println("1. View Paid Users");
        System.out.println("2. View Unpaid Users");
        
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter the Paid or UnPaid users:");
        String status = sc.nextLine();
        
        if(choice == 1 || choice == 2) {
        	List<RentDetails> paidUser = paymentRep.getPaidUsers(status);
        	System.out.println("------------- " + status + " Users --------------");
        	System.out.println("Rent_ID\tRoom_No\tName\tPhone_Number\tStatus");
            System.out.println("------------------------------------------------");
        	for(RentDetails paid: paidUser) {
        		System.out.println(paid.getRentId() + "\t" + paid.getRoomNumber() + "\t" + paid.getName() + "\t" + paid.getPhoneNumber() + "\t" + paid.getStatus());
        	}
        	System.out.println();
        } else  {
        	System.out.println("Invalid choice. Try again!");
        }
        
	}
}
