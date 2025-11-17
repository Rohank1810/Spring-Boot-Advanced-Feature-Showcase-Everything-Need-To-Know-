package com.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.model.Student;

@Service
public interface StudentService {

	Student addStudent(Student student);
	Optional<Student>getOneStudent(int id);
	List<Student>getStudents();
	boolean deleteStudent(int id);
	Student updateStudent(int id,Student student);
	List<Student>getAge(int age);
	List<Student>findByName(String name);
	List<Student>findByNameAge(String name,int age);
	List<Student>getAgeLessThan(int age);
	Student findByAge(int age);
	Student ageBetween(int min,int max);
	List<Student>getAllStudentByPagination(Integer pageNumber,Integer pageSize);
	List<Student>getAllStudentBySorting(String field);
	List<Student>getAllStudentBySortAndPagination(Integer pageNumber, Integer pageSize, String field);
}
