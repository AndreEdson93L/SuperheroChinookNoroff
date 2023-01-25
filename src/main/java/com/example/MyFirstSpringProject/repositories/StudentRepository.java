package com.example.MyFirstSpringProject.repositories;

import com.example.MyFirstSpringProject.models.Student;

import java.util.List;

//Generics
public interface StudentRepository extends CrudRepository<Integer, Student>{
    //Extra operations
    List<Student> getAllBySubject(int subjectId);
}
