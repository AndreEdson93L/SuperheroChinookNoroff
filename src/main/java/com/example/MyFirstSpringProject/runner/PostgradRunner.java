package com.example.MyFirstSpringProject.runner;

import com.example.MyFirstSpringProject.access.PostgradDAO;
import com.example.MyFirstSpringProject.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//When running, every CommandLineRunner will be taken in consideration and will run automatically ...
//... I simplify, and I can be wrong, with the main

//You can also use ApplicationRunner. It works as CommandLineRunner.
@Component
public class PostgradRunner implements CommandLineRunner {
    final PostgradDAO postgradDAO;
    @Autowired
    public PostgradRunner(PostgradDAO postgradDAO){

        this.postgradDAO = postgradDAO;
    }

    @Override
    public void run(String... args) throws  Exception{
        //System.out.println("Hello World Runner!");

        //test connection
        //postgradDAO.testConnection();

        //1)
        //postgradDAO.getAllCustomers();

        //2)
        //postgradDAO.getSpecificCustomer(1);

        //3
        //postgradDAO.getSpecificCustomerByName("Hele");

        //4
        //List<Customer> customerList = postgradDAO.getSetOfCustomers(10, 4);
        //System.out.println(customerList.get(1));

        //5
        //int last_customer_id = postgradDAO.getLastCustomerId();
        //System.out.println(last_customer_id);
        //postgradDAO.insertCustomer(new Customer(last_customer_id,"Andrea","Edson Lorenzoni","Italy","10134","+351 987 321 941","email@example.com"));

        //6
        //postgradDAO.updateCustomer(new Customer(3,"Giacomo","Terun terun","Italy","21345", "+43 123 142 123","trueGiacomino@gmail.com"));
        //postgradDAO.getSpecificCustomer(3);

        //7
        //System.out.println(postgradDAO.getCountryWithMostCustomers());
    }
}
