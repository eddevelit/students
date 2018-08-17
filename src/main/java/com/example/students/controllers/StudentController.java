package com.example.students.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students.entities.Student;
import com.example.students.exception.ResourceNotFoundException;
import com.example.students.repositories.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAll()
	{
		return studentRepository.findAll();
	}
	
	@PostMapping("/students")
	public Student create(@Valid @RequestBody Student student)
	{
		return studentRepository.save(student);
	}
	
	@GetMapping("/students/{id}")
	public Student getById(@PathVariable(value = "id") Long studentId)
	{
		return studentRepository.findById(studentId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", studentId));
	}
	
	@PutMapping("/students/{id}")
	public Student update (@PathVariable(value = "id") Long studentId, @Valid @RequestBody Student studentDetails)
	{
			Student student = studentRepository.findById(studentId)
					.orElseThrow(() -> new ResourceNotFoundException("Note", "id", studentId));
					
					student.setNombre(studentDetails.getNombre());
					student.setApellidoPaterno(studentDetails.getApellidoPaterno());
					student.setApellidoMaterno(studentDetails.getApellidoMaterno());
					student.setTelefono(studentDetails.getTelefono());
					
					Student updateStudent = studentRepository.save(student);
					return updateStudent;
	}
	
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long studentId)
	{
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", studentId));
		studentRepository.delete(student);
		
		return ResponseEntity.ok().build();
	}	
}