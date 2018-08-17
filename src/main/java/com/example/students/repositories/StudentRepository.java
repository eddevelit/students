package com.example.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.students.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
