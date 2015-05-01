package org.friendlytutor.inv01.controllers;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseErrorHandler {
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(SQLException e) {
		System.out.println(e.toString());
		return "error";
	}

	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException e) {
		System.out.println(e.toString());
		return "error";
	}

}
