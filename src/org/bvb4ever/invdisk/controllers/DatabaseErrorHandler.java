package org.bvb4ever.invdisk.controllers;

import java.net.ConnectException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseErrorHandler {
	
	private Logger logger = Logger.getLogger(DatabaseErrorHandler.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(Model model, 
			SQLException e, HttpServletRequest req) {
		logger.info("handleSQLException: "
				+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			
		String sErrorMsg = "SQL error: " + e.toString();
		System.out.println(sErrorMsg);
		
		model.addAttribute("sErrorMsg", sErrorMsg);
		model.addAttribute("url", req.getRequestURL().toString());
		return "error";
	}

	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(Model model, 
			DataAccessException e, HttpServletRequest req) {
		logger.info("handleDatabaseException: "
				+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		String sErrorMsg = "Data access error: " + e.toString();
		System.out.println(sErrorMsg);
		
		model.addAttribute("sErrorMsg", sErrorMsg);
		model.addAttribute("url", req.getRequestURL().toString());
		return "error";
	}

	@ExceptionHandler(CannotCreateTransactionException.class)
	public String handleTransactionException(Model model, 
			CannotCreateTransactionException e, HttpServletRequest req) {
		logger.info("handleTransactionException: "
				+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		String sErrorMsg = "Transaction error: " + e.toString();
		System.out.println(sErrorMsg);
		
		model.addAttribute("sErrorMsg", sErrorMsg);
		model.addAttribute("url", req.getRequestURL().toString());
		return "error";
	}

	@ExceptionHandler(ConnectException.class)
	public String handleConnectException(Model model, 
			ConnectException e, HttpServletRequest req) {
		logger.info("handleConnectException: "
				+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		String sErrorMsg = "Connection error: " + e.toString();
		System.out.println(sErrorMsg);
		
		model.addAttribute("sErrorMsg", sErrorMsg);
		model.addAttribute("url", req.getRequestURL().toString());
		return "error";
	}

}
