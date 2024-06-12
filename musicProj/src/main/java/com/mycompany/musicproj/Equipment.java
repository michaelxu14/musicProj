/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;

/**
 *
 * @author peter
 */
public class Equipment {
    private boolean signedOut;
    private String objectName;
    private int identifier;
    
    public Equipment(boolean signedOut, String objectName, int identifier){
        this.signedOut = signedOut;
        this.objectName = objectName;
        this.identifier = identifier;
    }

    /**
     * @return if it is signed out
     */
    public boolean isSignedOut() {
        return signedOut;
    }

    /**
     * @return the objectName
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * @return the identifier
     */
    public int getIdentifier() {
        return identifier;
    }
}
