package com.example.MyFirstSpringProject.models;

//the same in one line. I feel cool. I am indeed.
public record Student(int id, String name, int supervisor){

}
/*
public class Student {
    private String name;
    private int supervisor;
    private int id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
*/
