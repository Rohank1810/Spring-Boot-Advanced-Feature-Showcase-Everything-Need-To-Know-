package com.practice.exception;

public class StudentNotFoundException extends RuntimeException{

	public StudentNotFoundException(String msg)
	{
		super(msg);
	}
}
