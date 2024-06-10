/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;

/**
 *
 * @author Xu Last Name
 */
public class Instrument {
   private String name;
   private boolean signedOut; //True if instrument is signed out
   private int identifier; //Bar code stuff
   private int type; //Brass, strings, percussion, winds
   private double age;
   
   
   
   
   
   public Instrument(String name, boolean signedOut, int identifier, int type, double age){
       this.name = name;
       this.signedOut = signedOut;
       this.identifier = identifier;
       this.type = type;
       this.age = age;
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
     * @return the identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
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
     * @return the age
     */
    public double getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(double age) {
        this.age = age;
    }
   
   
}
