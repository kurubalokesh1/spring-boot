package com.Spring.PgManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Spring.PgManagement.Entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {

	// DELETE USER FROM TABLE
	@Modifying
    @Transactional
    @Query(value="delete from user where user_id = :userId and room_number = :roomNumber", nativeQuery = true)
	int deleteByUserIdAndRoomNumber(@Param("userId") int userId, @Param("roomNumber") String roomNumber);

	
}
