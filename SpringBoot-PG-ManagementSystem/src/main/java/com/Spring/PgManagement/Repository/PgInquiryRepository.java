package com.Spring.PgManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Spring.PgManagement.Entity.PgInquiry;

import jakarta.transaction.Transactional;

public interface PgInquiryRepository extends JpaRepository<PgInquiry,Integer> {

	// FIND IF USER EXISTED OR NOT
	
	@Query(value="Select count(*) from inquiry where name= :name and email= :email", nativeQuery = true)
	int userDetail(@Param("name") String name, @Param("email") String email);
	
	// FIND ID TAKE USER FEEDBACK AND UPDATE INTO TABLE
	@Query(value="Select inquiry_id from inquiry order by inquiry_id desc Limit 1",nativeQuery=true)
	int inqueryId();
	
	@Modifying
	@Transactional
	@Query(value="Update inquiry set interested= :interest where inquiry_id = :id",nativeQuery=true)
	int updateFeedback(@Param("interest") String inqueryId, @Param("id") int id);
}
