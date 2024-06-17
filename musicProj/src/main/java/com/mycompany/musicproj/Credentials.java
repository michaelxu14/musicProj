package com.mycompany.musicproj;

import java.util.Date;

/**
 *
 * @author peter
 */

public class Credentials {
    private int circid;  // Circulation ID
    private String sbarcode;
    private String ebarcode;
    private Date signout;
    private Date signin;

    // Constructor for signing out equipment

    /**
     *
     * @param circid
     * @param sbarcode
     * @param ebarcode
     * @param signout
     * @param signin
     */
    public Credentials(int circid, String sbarcode, String ebarcode, Date signout, Date signin) {
        this.circid = circid;
        this.sbarcode = sbarcode;
        this.ebarcode = ebarcode;
        this.signout = signout;
        this.signin = signin;
    }

    // Constructor for signing in equipment

    /**
     *
     * @param circid
     * @param signin
     */
    public Credentials(int circid, Date signin) {
        this.circid = circid;
        this.signin = signin;
    }

    // Getters and setters

    /**
     *
     * @return
     */
    public int getCircid() {
        return circid;
    }

    /**
     *
     * @param circid
     */
    public void setCircid(int circid) {
        this.circid = circid;
    }

    /**
     *
     * @return
     */
    public String getSbarcode() {
        return sbarcode;
    }

    /**
     *
     * @param sbarcode
     */
    public void setSbarcode(String sbarcode) {
        this.sbarcode = sbarcode;
    }

    /**
     *
     * @return
     */
    public String getEbarcode() {
        return ebarcode;
    }

    /**
     *
     * @param ebarcode
     */
    public void setEbarcode(String ebarcode) {
        this.ebarcode = ebarcode;
    }

    /**
     *
     * @return
     */
    public Date getSignout() {
        return signout;
    }

    /**
     *
     * @param signout
     */
    public void setSignout(Date signout) {
        this.signout = signout;
    }

    /**
     *
     * @return
     */
    public Date getSignin() {
        return signin;
    }

    /**
     *
     * @param signin
     */
    public void setSignin(Date signin) {
        this.signin = signin;
    }
}