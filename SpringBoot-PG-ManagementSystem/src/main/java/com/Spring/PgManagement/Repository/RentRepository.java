package com.Spring.PgManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Spring.PgManagement.Entity.Rent;

import jakarta.transaction.Transactional;

public interface RentRepository extends JpaRepository<Rent,String> {

	List<Rent> findAll();
	
	// If user book room update rent details table
	@Modifying
	@Transactional
	@Query(value="update rent_details set rent=: amount, status=: status where email= :email", nativeQuery=true)
	int payment(@Param("amount") double amount, @Param("status") String status,@Param("email") String email);
}
