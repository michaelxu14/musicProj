/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;

/**
 *
 * @author peter
 */
public class Student {

    private String name;
    private int id;
    private int grade;

    /**
     *
     * @param name
     * @param id
     * @param grade
     */
    public Student(String name, int id, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getGrade() {
        return grade;
    }

    /**
     *
     * @param grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
}
