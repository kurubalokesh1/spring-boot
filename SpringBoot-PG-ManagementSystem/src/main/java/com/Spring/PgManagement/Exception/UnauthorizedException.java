package com.Spring.PgManagement.Exception;

public class UnauthorizedException extends Exception {
	
	public String getMessage() {
		
		return "You don’t have permission";
		
	}

}
