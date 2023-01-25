package com.example.MyFirstSpringProject.repositories;

import com.example.MyFirstSpringProject.models.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private final String url;
    private final String username;
    private final String password;

    public StudentRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
            )
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Student getById(Integer integer) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public void create(Student object) {

    }

    @Override
    public void update(Student object) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Student> getAllBySubject(int subjectId) {
        return null;
    }
}
