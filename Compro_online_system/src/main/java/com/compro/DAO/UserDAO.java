/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.DAO;


import static com.compro.DAO.ApplicationDAO.con;
import com.compro.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nesanet
 */
public class UserDAO {
    
     static Connection con;
     
    static User getAccount(int id){
        User user=new User();
        boolean flag = false;
        try
        {
        con = ConnectionManager.dcConnect();
        con.setAutoCommit(false);
        Statement statement = null;
        String SQL = "Select * from `user` "
                    + "where id ="+id;
                    
                    
        statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

        if (rs != null) 
            {   
               // retrieving the data from result set
                while (rs.next())
                {
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("fName"));
                    user.setSurname(rs.getString("lName"));
                    user.setMiddlename(rs.getString("mName"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("pasword"));
                    user.setRole(rs.getString("role"));
                    flag = true;

                }
                if(!flag){
                     return null;
                }
            } 
                
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

        return user;
    }
    
    static boolean changePassword(String password, int id){
        
        try{
        con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
        String SQL ="update user set pasword='"+password
                    +"' where id="+id;
        statement = con.createStatement();
         int res=   statement.executeUpdate(SQL);
           
            if(res>0){
             con.commit();
             return true;
            
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return false;
    }
    
    static User getUserByEamil(String email){
        User user=new User();
        boolean flag = false;
        try
        {
        con = ConnectionManager.dcConnect();
        con.setAutoCommit(false);
        Statement statement = null;
        String SQL = "Select * from user "
                    + "where email = '"+ email +"'";
                    
        statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

        if (rs != null) 
            {   
               // retrieving the data from result set
                while (rs.next())
                {
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("fName"));
                    user.setSurname(rs.getString("lName"));
                    user.setMiddlename(rs.getString("mName"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("pasword"));
                    user.setRole(rs.getString("role"));
                    flag = true;

                }
                if(!flag){
                     return null;
                }
            } 
                
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

        return user;
    }
}
