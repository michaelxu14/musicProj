/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;

/**
 *
 * @author Xu Last Name
 */
public class Appliance {
    private String name;
    private boolean signedOut; // True if instrument is signed out
    private int id; // Bar code stuff
    private int type; // Brass, strings, percussion, winds
    private int year;

    public Appliance(String name, boolean signedOut, int id, int type, int year) {
        this.name = name;
        this.signedOut = signedOut;
        this.id = id;
        this.type = type;
        this.year = year;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the signedOut
     */
    public boolean isSignedOut() {
        return signedOut;
    }

    /**
     * @param signedOut the signedOut to set
     */
    public void setSignedOut(boolean signedOut) {
        this.signedOut = signedOut;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
}