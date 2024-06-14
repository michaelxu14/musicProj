/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;

/**
 *
 * @author Xu Last Name
 */
public class Credentials {
    private int circid;
    private String sbarcode;
    private String ebarcode;
    private String signout;
    private String signin;

    // Constructor
    public Credentials(int circid, String sbarcode, String ebarcode, String signout, String signin) {
        this.circid = circid;
        this.sbarcode = sbarcode;
        this.ebarcode = ebarcode;
        this.signout = signout;
        this.signin = signin;
    }

    // Getter and Setter methods
    public int getCircid() {
        return circid;
    }

    public void setCircid(int circid) {
        this.circid = circid;
    }

    public String getSbarcode() {
        return sbarcode;
    }

    public void setSbarcode(String sbarcode) {
        this.sbarcode = sbarcode;
    }

    public String getEbarcode() {
        return ebarcode;
    }

    public void setEbarcode(String ebarcode) {
        this.ebarcode = ebarcode;
    }

    public String getSignout() {
        return signout;
    }

    public void setSignout(String signout) {
        this.signout = signout;
    }

    public String getSignin() {
        return signin;
    }

    public void setSignin(String signin) {
        this.signin = signin;
    }
}

