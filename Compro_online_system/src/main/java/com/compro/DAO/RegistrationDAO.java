/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.compro.model.User;

/**
 *
 * @author asingh
 */
public class RegistrationDAO 
{
    static Connection con;
    
    
    
    
     static boolean registration(User user) throws SQLException
    {
        LoginDAO login = new LoginDAO();
        boolean flag = false;
        String mname = "";
        String fname = user.getName();
        String lname = user.getSurname();
        String email = user.getEmail();
        String password = user.getPassword();
        
        if(user.getMiddlename().equals(""))
            mname = "";
        else
            mname = user.getMiddlename();
            
        
        try
        {
        flag = login.validateRegistrationData(email);
        
        if(flag == true)
            return false;
            
        ConnectionManager connectionMan = new ConnectionManager();
        con = ConnectionManager.dcConnect();
        con.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        String SQL = "Insert into compro.user (fName, lName, mNAme, email, pasword) "
                    + " VALUES (?,?,?,?,?)";
                    
        preparedStatement = con.prepareStatement(SQL);
        preparedStatement.setString(1, fname);
        preparedStatement.setString(2, lname);
        preparedStatement.setString(3, mname);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, password);
        
        int rs = preparedStatement.executeUpdate();
        
        if(rs == 0)
            return false;
                
        con.commit();
            
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            if (con != null) 
            {
                try
                {               
                    con.rollback();
                } 
                catch (SQLException ex)
                {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }

        } 
        
        return true;
     } 
    
}
