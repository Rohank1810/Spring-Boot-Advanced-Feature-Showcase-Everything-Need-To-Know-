package com.practice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.DTO.ErrorResponse;
import com.practice.exception.StudentNotFoundException;
import com.practice.model.Student;
import com.practice.service.StudentServiceImpl;

@RestController
@RequestMapping("/newapi")
public class SecondController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@GetMapping("/student/{min}/{max}")
	public ResponseEntity<Student>getAgeBetwen(@PathVariable int min,@PathVariable int max)
	{
		Student s=studentServiceImpl.ageBetween(min, max);
	    if (s == null) {
	        throw new StudentNotFoundException("Student  not found");
	    }
	    return ResponseEntity.ok(s);
	}
	
	
	@GetMapping("/students/page")
	public ResponseEntity<List<Student>>getStudentByPagination(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize)
	{
		return ResponseEntity.ok(studentServiceImpl.getAllStudentByPagination(pageNum, pageSize));
	}
	
	@GetMapping("/students/sort")
	public ResponseEntity<List<Student>>getStudentBySort(@RequestParam("field")String field)
	{
		return ResponseEntity.ok(studentServiceImpl.getAllStudentBySorting(field));
	}
	@GetMapping("/students/ps/{pageNum}/{pageSize}/{field}")
	public ResponseEntity<List<Student>>getAllStudentByPageSort(@PathVariable(required = false) Integer pageNum ,@PathVariable(required = false) Integer pageSize
			,@PathVariable(required = false) String field)
	{
		if (pageNum==null) pageNum=0;
		if (pageSize==null) pageSize=3;
		if (field==null) field="id";
		return ResponseEntity.ok(studentServiceImpl.getAllStudentBySortAndPagination(pageNum, pageSize, field));
	}
//	@ExceptionHandler(StudentNotFoundException.class)
//	public ResponseEntity<ErrorResponse> StudentNotFound(StudentNotFoundException e)
//	{
//		ErrorResponse err=new ErrorResponse("Not found student",LocalDate.now(),e.getMessage());
//		return ResponseEntity.ok(err);
//	}

}
