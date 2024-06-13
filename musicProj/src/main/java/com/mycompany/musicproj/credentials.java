package com.mycompany.musicproj;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author peter
 */
public class credentials {
    private String username;
    private int sbarcode;
    private date signin;
    private date signout;
    public credentials(String username, int sbarcode, date signin, date signout){
        this.username = username;
        this.sbarcode = sbarcode;
        this.signin = signin;
        this.signout = signout;
    }
}
