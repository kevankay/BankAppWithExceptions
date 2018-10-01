package com.capgemini.bankapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankapp.exception.CustomerNotFoundException;

@ControllerAdvice
	public class ExceptionController {

		@ExceptionHandler(value = CustomerNotFoundException.class)
		public String handlheError(HttpServletRequest request, CustomerNotFoundException exception) {

			request.setAttribute("error", exception);

			return "error";
		}
	}


