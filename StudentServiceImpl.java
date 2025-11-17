package com.practice.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.practice.model.Student;
import com.practice.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
		System.out.println("Inside student add service");
		return studentRepository.save(student);
	}

	@Override
	@Cacheable(value="students",key="#id" )
	public Optional<Student> getOneStudent(int id) {
		System.out.println("Method call");
		return studentRepository.findById(id);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	@CacheEvict(value="students",key="#id")
	public boolean deleteStudent(int id) {
		Student s=studentRepository.findById(id).orElse(null);
		if(s!=null)
		{
			studentRepository.delete(s);
			return true;
		}
		return false;
	}

	@Override
	@CachePut(value="students",key="#id")
	public Student updateStudent(int id, Student student) {
		Student old=studentRepository.findById(id).orElse(null);
		if(old!=null)
		{
			old.setAge(student.getAge());
            old.setCity(student.getCity());
            old.setName(student.getName());
            studentRepository.save(old);
            return old;
		}
		return student;
	}

	@Override
	public List<Student> getAge(int age) {
		return studentRepository.findByAgeGreaterThan(age);
	}

	@Override
	public List<Student> findByName(String name) {
		return studentRepository.findByName(name);
	
	}

	@Override
	public List<Student> findByNameAge(String name, int age) {
		
		return studentRepository.findByNameAge(name,age);
	}

	@Override
	public List<Student> getAgeLessThan(int age) {
		
		return studentRepository.getStudentAge(age);
	}

	@Override
	public Student findByAge(int age) {
		return studentRepository.findByAge(age);
	}

	@Override
	public Student ageBetween(int min, int max) {
		return studentRepository.findAgeBetween(min,max);
		
	}

	@Override
	public List<Student> getAllStudentByPagination(Integer pageNumber, Integer pageSize) {
	
		Page<Student>page=studentRepository.findAll(PageRequest.of(pageNumber, pageSize));
		return page.getContent();
	}

	@Override
	public List<Student> getAllStudentBySorting(String field) {
		return studentRepository.findAll(Sort.by(Sort.Direction.ASC,field));
	}

	@Override
	public List<Student> getAllStudentBySortAndPagination(Integer pageNumber, Integer pageSize, String field) {
		Page<Student>page=studentRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));
		return page.getContent();
	}
	
	
	
	
	

}
