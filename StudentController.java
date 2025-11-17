package com.practice.controller;

import java.lang.System.Logger;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.DTO.ErrorResponse;
import com.practice.exception.StudentNotFoundException;
import com.practice.model.Student;
import com.practice.repository.StudentRepository;
import com.practice.service.StudentServiceImpl;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository studentRepository;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StudentController.class);

    StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

	@GetMapping("/check")
	public String check()
	{
		logger.info("inside Check");
		return "hi";
	}
	
	@PostMapping("/student")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student)
	{
		logger.info("Student name"+student.getName());
		studentServiceImpl.addStudent(student);
//		return new ResponseEntity<Student>(student,HttpStatus.CREATED);
		logger.info("Student added");
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> getStudent(@PathVariable int id) {
	//	try {
	    Student found = studentServiceImpl.getOneStudent(id)
	            .orElseThrow(() -> new StudentNotFoundException("Student with " + id + " not found"));
	    return ResponseEntity.ok(found); 
	    
		//}
//		catch(StudentNotFoundException e)
//		{
//		ErrorResponse error=new ErrorResponse("Not found student",LocalDate.now(),e.getMessage());
//		 return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//		}
//		if(found!=null)
//		{
//			//return new ResponseEntity<Student>(found,HttpStatus.FOUND);
//			return ResponseEntity.status(HttpStatus.FOUND).body(found);
//		}
//		return new ResponseEntity<Student>(new Student(),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents()
	{
		List<Student>list=studentServiceImpl.getStudents();
		System.out.println(list);
		return new ResponseEntity<List<Student>>(list,HttpStatus.FOUND);
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student>updateStudent(@PathVariable int id,@RequestBody Student student)
	{
		System.out.println(student.getAge());
		return new ResponseEntity<Student>(studentServiceImpl.updateStudent(id, student),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Boolean>deleteStudent(@PathVariable int id)
	{
		long start = System.currentTimeMillis();
		
        boolean r=studentServiceImpl.deleteStudent(id);
			logger.info("Processing took {} ms", System.currentTimeMillis() - start);
			return new ResponseEntity<Boolean>(r,HttpStatus.FOUND);

	}
	
	@GetMapping("/studentAge/{age}")
	public ResponseEntity<List<Student>>getStudentAgeGreaterThan(@PathVariable int age)
	{
		List<Student>greater=studentServiceImpl.getAge(age);
	    return ResponseEntity.ok(greater);
	}
	
	@GetMapping("/studentName/{name}")
	public ResponseEntity<List<Student>>findByName(@PathVariable String name)
	{
		List<Student>get= studentServiceImpl.findByName(name);
		return ResponseEntity.ok(get);
	}
	
	@GetMapping("/studentNameAge/{name}/{age}")
	public ResponseEntity<List<Student>>findByNameAge(@PathVariable String name,@PathVariable int age)
	{
		List<Student>get= studentServiceImpl.findByNameAge(name,age);
		return ResponseEntity.ok(get);
	}
	
	@GetMapping("/less/{age}")
	public ResponseEntity<List<Student>>findByAgeLess(@PathVariable int age)
	{
		List<Student>get= studentServiceImpl.getAgeLessThan(age);
		return ResponseEntity.ok(get);
	}
	
	@GetMapping("/studentGetByAge/{age}")
	public ResponseEntity<?> findByAge(@PathVariable int age) {
	    Student student = studentServiceImpl.findByAge(age);
	    if (student == null) {
	        throw new StudentNotFoundException("Student with age " + age + " not found");
	    }
	    return ResponseEntity.ok(student);
		
	}
	
	
//	@ExceptionHandler(StudentNotFoundException.class)
//	public ResponseEntity<ErrorResponse> StudentNotFound(StudentNotFoundException e)
//	{
//		ErrorResponse err=new ErrorResponse("Not found student",LocalDate.now(),e.getMessage());
//		return ResponseEntity.ok(err);
//	}

	
	
	
}
