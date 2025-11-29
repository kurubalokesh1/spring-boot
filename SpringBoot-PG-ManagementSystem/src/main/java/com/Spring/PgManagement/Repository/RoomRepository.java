package com.Spring.PgManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Spring.PgManagement.Entity.Admin;
import com.Spring.PgManagement.Entity.Room;

import jakarta.transaction.Transactional;

public interface RoomRepository extends JpaRepository<Room,String> {

	// FETCH BEDS VACANIES
	 List<Room> findByAvailableBedsGreaterThan(int beds);
	 
     // UPDATE ROOM IF RECORDS INSERTED
	 @Modifying
	 @Transactional
	 @Query(value="update room set occupied_count = (occupied_count+1),available_beds = (available_beds-1) where room_number = :roomNumber",nativeQuery = true)
	 int updateRoom(@Param("roomNumber") String room_number);

	 
	 // UPDATE ROOM IF USER DELETED RECORD
	 @Modifying
	 @Transactional
	 @Query(value="update room set occupied_count = (occupied_count-1),available_beds = (available_beds+1) where room_number = :roomNumber",nativeQuery = true)
	 int deleteRoom(@Param("roomNumber") String room_number);

	// UPDATE ROOMS(EXISTED ROOMS)
	 @Modifying
	 @Transactional
	 @Query(value="update room set room_type = :roomSharing, capacity = (capacity - 1) where room_number = :roomNumber", nativeQuery = true)
	 int updateRooms(@Param("roomSharing") String room_type, @Param("roomNumber") String room_number);
	
	 // DELETE RECORD FROM THE TABLE
	 @Modifying
	 @Transactional
	 int deleteByRoomNumber(String room_number);
}
