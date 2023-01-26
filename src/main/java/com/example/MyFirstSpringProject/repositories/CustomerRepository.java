package com.example.MyFirstSpringProject.repositories;

import com.example.MyFirstSpringProject.models.Customer;

import java.util.List;

//Generics
public interface CustomerRepository extends CrudRepository<Integer, Customer>{
    //Extra operations
    List<Customer> getAllByCountry(String country);
}
