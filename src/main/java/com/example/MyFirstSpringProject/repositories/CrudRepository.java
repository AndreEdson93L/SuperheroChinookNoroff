package com.example.MyFirstSpringProject.repositories;

import java.util.List;

public interface CrudRepository <ID, T>{
    //CRUD
    T getById(ID id);
    List<T> getAll();
    void create(T object);
    void update(T object);
    void delete(ID id);
}
