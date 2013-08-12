/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.DAO;

import com.compro.model.ApplicationForm;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asingh
 */
public class AdmissionStaffDAO {
    
    static Connection con = null;
    
    
   //Testing Purpose    
//    public static void main(String[] argv) throws SQLException
//    {
//        boolean boo = false; 
//        AdmissionStaffDAO add = new AdmissionStaffDAO(); 
//         boo = add.overrideDisposition("pass", "no change", "4");
//         System.out.println(boo);
//         
//    }
            
    
    static List<ApplicationForm> getAllApplication() 
    {
        
        List<ApplicationForm> listOfAppForm = new ArrayList<ApplicationForm>();
        try{


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "SELECT * FROM application_form";
                    
            System.out.println(SQL);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if (rs != null) {
                while(rs.next()) {
                    ApplicationForm appForm = new ApplicationForm();
                    appForm = new ApplicationForm(rs.getInt("id"), rs.getString("status"), rs.getString("disposition"),
                            rs.getDate("creation_date"), rs.getDate("last_modified_date"), rs.getInt("user_id"));

                    listOfAppForm.add(appForm);
                }
            }
            con.commit();

        } 
        
        catch (Exception e) {
            System.out.println(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return listOfAppForm;
        
    }
    
    static ApplicationForm getUserApplicationWithDisposition(String userId) {
        ApplicationForm appForm = null;
        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "select * from application_form "
                    + "where user_id = " + userId;

            System.out.println(SQL);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if (rs != null) {
                if (rs.next()) {
                    appForm = new ApplicationForm(rs.getInt("id"), rs.getString("status"), rs.getString("disposition"),
                            rs.getDate("creation_date"), rs.getDate("last_modified_date"), rs.getInt("user_id"));

                    appForm = getApplicationFormFields(appForm);
                }
            }
            con.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return appForm;
    }
    
    
    
    static ApplicationForm getApplicationFormFields(ApplicationForm applicationForm) {
        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "select * from field_form "
                    + "where application_form_id = " + applicationForm.getId();

            System.out.println(SQL);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    /*Field field = null;
                    String SQL2 = "select * from field "
                            + "where id = " + fieldId;
                    System.out.println(SQL2);

                   // statement = con.createStatement();
                    ResultSet rs2 = statement.executeQuery(SQL2);
                    if (rs2 != null) {
                        if (rs2.next()) {
                            System.out.println("xxxx");
                            field = new Field(rs2.getInt("id"), rs2.getString("name"), rs2.getString("type"),
                                    rs2.getInt("order"), rs2.getString("status"), new Section());
                            System.out.println("yyyy");
                        }
                    }
*/
                    //FieldForm fieldForm = new FieldForm(rs.getInt("id"), rs.getString("value"), applicationForm, null);
                    applicationForm.getFieldsValues().put(new Integer(rs.getInt("field_id")), rs.getString("value"));
                    System.out.println("yyyy");
                }
            }

            System.out.println("xxx"+applicationForm.getFieldsValues().size());
            con.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return applicationForm;
    }
    
    public boolean overrideDisposition(String disposition, String status, String userId)
    {
        try{


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);
            String SQL = null;

            PreparedStatement preparedStatement = null;
           
            if(!status.equalsIgnoreCase("noChange"))
            {
                SQL = "Update application_form "
                        + "set  disposition = '"+ disposition +"'"
                        + ", status = '"+ status +"' "
                        + "where user_id = '"+ userId +"'";
            }
            else
            {
               SQL = "Update application_form "
                        + "set  disposition = '"+ disposition +"'"
                        + "where user_id = '"+ userId +"'";
            }
            
                              
            System.out.println(SQL);

            preparedStatement = con.prepareStatement(SQL);
            int rs = preparedStatement.executeUpdate();
          
            if(rs == 0)
            return false;
            
            
            con.commit();

        } 
        
        catch (Exception e) {
            System.out.println(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
         return true;

    }
    
   
    
    
    }
