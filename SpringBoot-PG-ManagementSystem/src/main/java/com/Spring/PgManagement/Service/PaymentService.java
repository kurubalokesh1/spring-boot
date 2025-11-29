package com.Spring.PgManagement.Service;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.Repository.PaymentRepository;

@Component
public class PaymentService {

	@Autowired
	PaymentRepository paymentRep;
	
	@Autowired
	MailSender mailSender;
	
	public void processPayment() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Rent Amount:");
		double amount = sc.nextDouble();
		sc.nextLine();
		System.out.print("Enter the Room Number:");
		String room = sc.nextLine();
		System.out.print("Enter name of the user:");
		String name = sc.nextLine();
		System.out.print("Enter the Email ID:");
		String email = sc.nextLine();
		
		// EMAIL SEND TO USER
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("💳 Payment Successful - Sunrise PG");
		message.setFrom("Sunrise PG 🏢 <lokeshkuruba18@gmail.com>");
		message.setTo(email);
		message.setText("Dear " + name + ",\n\n" + "Your payment for this month has been received successfully. 😊\n\n");
		
		try { 	
		  if(amount > 0) {
			  mailSender.send(message);
		      int payment = paymentRep.makePayment(amount,LocalDate.now(),"paid",room,name);
		      System.out.println("✅ Payment Successful! Rent details updated for user: " + name);
		  }
		} catch(Exception e) {
			System.out.println("Payment failed. Try Again!");
		}
	}
}
