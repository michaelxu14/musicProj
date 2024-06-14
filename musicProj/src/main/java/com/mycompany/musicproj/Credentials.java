/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;
import java.util.Date;
/**
 *
 * @author peter
 */
    
public class Credentials {
    private int circid;
    private String sbarcode;
    private String ebarcode;
    private Date signout;
    private Date signin;

    // Constructor
    public Credentials(int circid, String sbarcode, String ebarcode, Date signout, Date signin) {
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

    public Date getSignout() {
        return signout;
    }

    public void setSignout(Date signout) {
        this.signout = signout;
    }

    public Date getSignin() {
        return signin;
    }

    public void setSignin(Date signin) {
        this.signin = signin;
    }
}

