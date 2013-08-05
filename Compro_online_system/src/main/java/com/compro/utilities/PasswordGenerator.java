/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.utilities;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class PasswordGenerator {
    
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

   static  public String getPassword(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        System.out.println("password generated = " + sb.toString());
        return sb.toString();
    }
}
       
  
