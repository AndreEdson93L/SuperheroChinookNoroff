package com.example.MyFirstSpringProject.repositories;

import com.example.MyFirstSpringProject.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
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
    public Customer getById(Integer integer) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void create(Customer object) {

    }

    @Override
    public void update(Customer object) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Customer> getAllByCountry(String country) {
        return null;
    }
}
