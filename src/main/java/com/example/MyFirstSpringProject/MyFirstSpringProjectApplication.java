package com.example.MyFirstSpringProject;

import com.example.MyFirstSpringProject.access.PostgradDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFirstSpringProjectApplication {
	public static void main(String[] args) {

		SpringApplication.run(MyFirstSpringProjectApplication.class, args);
		//var postgradDAO = new PostgradDAO();
		//postgradDAO.getStudentById(1);
		//postgradDAO.testConnection();
	}
}
