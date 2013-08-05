/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.DAO;

import java.sql.SQLException;
import com.compro.model.User;

/**
 *
 * @author asingh
 */
public class DAOFacade {
      
    public User login(String email, String passWord) throws SQLException
    {
        User user ;
        boolean flag = false;
        LoginDAO login = new LoginDAO();
        user = login.login(email, passWord);
        return user;
        
    }
    
    public boolean registration(User user) throws SQLException
    {
        System.out.println("ddddddd");
        boolean flag = false;
        RegistrationDAO registration = new RegistrationDAO();
        flag = registration.registration(user);
        return flag;
        
    }
    
}
