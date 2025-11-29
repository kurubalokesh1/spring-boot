package com.Spring.PgManagement.Service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Spring.PgManagement.Entity.Admin;
import com.Spring.PgManagement.Entity.Room;
import com.Spring.PgManagement.Exception.UnauthorizedException;
import com.Spring.PgManagement.Repository.AdminRepository;
import com.Spring.PgManagement.Repository.RoomRepository;

@Component
public class RoomService {

	@Autowired
	RoomRepository roomRep;
	
	@Autowired
	AdminRepository adminRepo;

	Scanner sc = new Scanner(System.in);
	public void adminAccess() throws UnauthorizedException {
		
	    System.out.print("Enter your admin id: ");
	    int enteredId = sc.nextInt();

	    Admin admin = adminRepo.findByPgName("Sunrise PG");
	    int actualAdminId = admin.getPgId();

	    if (enteredId == actualAdminId) {
	        System.out.println("Permission Access — You can update rooms");
	    } else {
	        throw new UnauthorizedException();
	    }
	}

	// ADD ROOMS
	public void AddRoom() {
        Admin pgName = adminRepo.findByPgName("Sunrise PG");
        if (pgName != null) {
            Room room1 = new Room("R201", "Double", 2, 2, 0, 2, pgName);
            Room room2 = new Room("R301", "Triple", 3, 3, 0, 3, pgName);
            List<Room> row = roomRep.saveAll(List.of(room1, room2));
            System.out.println(row.size() + " Room(s) added successfully!");
        } else {
            System.out.println("❌ Admin not found! Please add Admin first.");
        }
    }
	
	// UPDATE ROOMS
	public void updateRoom() {
		
		System.out.print("Enter the Room Sharing:");
		String sharing = sc.nextLine();
		System.out.print("Enter the Room Number:");
		String room_nubmer = sc.nextLine();
		int roomUpdated = roomRep.updateRooms(sharing,room_nubmer);
		System.out.println(roomUpdated + " row affected Successfully!");
	}
	
	// DELETE RECORD FROM THE TABLE
	public void deleteRoom() {
		System.out.print("Enter the Room Number:");
		String roomNumber = sc.nextLine();
		int deletedRoom = roomRep.deleteByRoomNumber(roomNumber);
		System.out.println(deletedRoom + " row affected Successfully!");
	}
}
