package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

	List<Student>findByAgeGreaterThan(int age);
	List<Student>findByName(String name);
	
	Student findByAge(int age);
	
	@Query(value="SELECT * from studentrpnew WHERE name=:name AND age=:age",nativeQuery = true)
	List<Student>findByNameAge(@Param("name") String name,@Param("age")int age);
	
	@Query("SELECT s FROM Student s where s.age<:age")
	List<Student> getStudentAge(@Param("age") int age);
	
	@Query("SELECT s FROM Student s WHERE s.age > :min AND s.age < :max")
	Student findAgeBetween(@Param("min") int min, @Param("max") int max);

	
	
	
}
