package com.mycompany.musicproj;

import java.util.Date;

public class Credentials {
    private int circid;  // Circulation ID
    private String sbarcode;
    private String ebarcode;
    private Date signout;
    private Date signin;

    // Constructor for signing out equipment
    public Credentials(int circid, String sbarcode, String ebarcode, Date signout, Date signin) {
        this.circid = circid;
        this.sbarcode = sbarcode;
        this.ebarcode = ebarcode;
        this.signout = signout;
        this.signin = signin;
    }

    // Constructor for signing in equipment
    public Credentials(int circid, Date signin) {
        this.circid = circid;
        this.signin = signin;
    }

    // Getters and setters
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