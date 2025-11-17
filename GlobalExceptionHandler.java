package com.practice.exception;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.practice.DTO.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> StudentNotFound(StudentNotFoundException e)
	{
		ErrorResponse err=new ErrorResponse("Not found student",LocalDate.now(),e.getMessage());
		return ResponseEntity.ok(err);
	}
}
