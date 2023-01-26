package com.example.MyFirstSpringProject.repositories;

import java.util.List;

public interface CrudRepository <ID, T>{
    //CRUD
    List<T> getAll();
    T getById(ID id);
    void insert(T object);
    void update(T object);
    //void delete(T object);
    //int deleteById(U id);
}
