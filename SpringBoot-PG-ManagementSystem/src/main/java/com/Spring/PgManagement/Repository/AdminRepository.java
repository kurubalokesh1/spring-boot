package com.Spring.PgManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.PgManagement.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

	Admin findByPgName(String PgName);

	
}
