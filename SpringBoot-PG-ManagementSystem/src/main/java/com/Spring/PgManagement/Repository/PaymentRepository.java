package com.Spring.PgManagement.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Spring.PgManagement.Entity.RentDetails;

import jakarta.transaction.Transactional;

public interface PaymentRepository extends JpaRepository<RentDetails,Integer> {

	    @Modifying
	    @Transactional
	    @Query(value = "UPDATE rent_details " +
	                   "SET rent = :amount, rent_date = :date, status = :status " +
	                   "WHERE room_number = :roomNumber AND name = :name",
	           nativeQuery = true)
	    int makePayment(
	        @Param("amount") double amount,
	        @Param("date") LocalDate date,
	        @Param("status") String status,
	        @Param("roomNumber") String roomNumber,
	        @Param("name") String name
	    );
	    
	    // RETERIVE PAID USER RECRODS FROM TABLE
	    @Query(value="select *from rent_details where status= :status", nativeQuery=true)
	    List<RentDetails> getPaidUsers(@Param("status") String status);
	    
//	    // RETERIVE UNPAID USER RECORDS FROM TABLE
//	    @Query(value="select *from rent_details where status= :status", nativeQuery=true)
//	    List<RentDetails> getUnpaidUsers(@Param("status") String status);
}
