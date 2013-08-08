/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.DAO;

/**
 *
 * @author Waseem
 */
import com.compro.model.Application;
import com.compro.model.ApplicationForm;
import com.compro.model.Field;
import com.compro.model.FieldForm;
import com.compro.model.Section;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationDAO {

    static Connection con = null;

    static Application getApplicationTemplate() throws SQLException {
        Application appTemplate = null;
        try {
            
            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "select * from application "
                    + "where status = 'active' ";

            System.out.println(SQL);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if (rs != null) {
                appTemplate = new Application();
                if (rs.next()) {
                    appTemplate.setId(rs.getInt("id"));
                    appTemplate.setCreationDate(rs.getDate("creation_date"));
                    appTemplate.setDisposition(rs.getString("disposition"));
                    appTemplate.setLastModifiedDate(rs.getDate("last_modified_date"));
                    appTemplate.setStatus(rs.getString("status"));
                    appTemplate = getApplicationSectionsTemplate(appTemplate);
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

        return appTemplate;
    }

    static Application getApplicationSectionsTemplate(Application appTemplate) throws SQLException {
        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "select * from section "
                    + "where status = 'active' and application_id = " + appTemplate.getId() + " order by section.order";

            System.out.println(SQL);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    Section sec = new Section(rs.getInt("id"), rs.getString("name"), rs.getString("status"),
                            rs.getInt("order"), appTemplate);
                    sec = getSectionsFieldsTemplate(sec);
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

        return appTemplate;
    }

    static Section getSectionsFieldsTemplate(Section secTemplate) throws SQLException {
        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "select * from field "
                    + "where status = 'active' and section_id = " + secTemplate.getId() + " order by field.order";

            System.out.println(SQL);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {
                    Field field = new Field(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                            rs.getInt("order"), rs.getString("status"), secTemplate);
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

        return secTemplate;
    }

    static ApplicationForm getUserApplication(String userId) {
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

    static boolean insertApplicationForm(ApplicationForm applicationForm) {
        boolean saved = false;

        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "insert into application_form (user_id,creation_date,last_modified_date,status,disposition) "
                    + " values("+applicationForm.getUserId()+",now(),now(),'"+applicationForm.getStatus()+"','"+applicationForm.getDisposition()+"') ";

            System.out.println(SQL);

            statement = con.createStatement();
            statement.executeUpdate(SQL);
            con.commit();
            
            String SQL2 = "select * from application_form "
                    + "where user_id = " + applicationForm.getUserId();

            System.out.println(SQL2);

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL2);
            if (rs != null) {
                if (rs.next()) {
                    int appFormId = rs.getInt("id");
                    applicationForm.setId(appFormId);
                }
            }
            con.commit();
            
            
            for(int i=0;i<applicationForm.getFieldsForm().size();i++)
            {
                FieldForm fieldForm = (FieldForm)applicationForm.getFieldsForm().get(i);
                insertApplicationFieldForm(fieldForm);
            }
            
            saved = true;

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

        return saved;
    }
    
    
        static boolean updateApplicationForm(ApplicationForm applicationForm) {
        boolean saved = false;

        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "update application_form set last_modified_date = now(),"+
                    " status = '"+applicationForm.getStatus()+"',"+
                    " disposition= '"+applicationForm.getDisposition()+"' "+
                    " where id = "+applicationForm.getId();

            System.out.println(SQL);

            statement = con.createStatement();
            statement.executeUpdate(SQL);
            con.commit();
            
            for(int i=0;i<applicationForm.getFieldsForm().size();i++)
            {
                FieldForm fieldForm = (FieldForm)applicationForm.getFieldsForm().get(i);
                updateApplicationFieldForm(fieldForm);
            }
            
            saved = true;

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

        return saved;
    }
        
        
       static boolean insertApplicationFieldForm(FieldForm fieldForm) {
        boolean saved = false;

        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            String SQL = "insert into field_form (application_form_id,field_id,value) "
                    + " values("+fieldForm.getApplicationForm().getId()+","+fieldForm.getField().getId()+",'"+fieldForm.getValue()+"') ";

            System.out.println(SQL);

            statement = con.createStatement();
            statement.executeUpdate(SQL);
            con.commit();
            saved = true;

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

        return saved;
    }
       
       
       static boolean updateApplicationFieldForm(FieldForm fieldForm) {
        boolean saved = false;

        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            
            String SQL1 = "select * from field_form where field_id = "+fieldForm.getField().getId()+" and application_form_id = "+fieldForm.getApplicationForm().getId();
            System.out.println(SQL1);        

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL1);
            if (rs != null) {
                if (rs.next()) {
                    String SQL = "update field_form set value = '"+fieldForm.getValue()+"' "
                    + " where field_id = "+fieldForm.getField().getId()+" and application_form_id = "+fieldForm.getApplicationForm().getId();
                    System.out.println(SQL);

                    Statement statement2 = con.createStatement();
                    statement2.executeUpdate(SQL);
                }else
                {
                    insertApplicationFieldForm(fieldForm);
                }
            }
            else {
                insertApplicationFieldForm(fieldForm);
            }
            
            con.commit();
            saved = true;

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

        return saved;
    } 
       
       static String checkRules(ApplicationForm applicationForm){
           String result="undetermined";
           try {
            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);
            
            String SQL = "select * from field_rule";
            Statement statement = null;
            Statement statement2 = null;
            statement = con.createStatement();
            statement2 = con.createStatement();
            ResultSet rs1 = statement.executeQuery(SQL);
            ResultSet rs2;
             if (rs1 != null) {
                 int field_id;
                 int limit;
                 String disposition;
                 String operator;
                    while (rs1.next()) {
                    field_id = rs1.getInt("field_id");
                    limit = rs1.getInt("value");
                    disposition = rs1.getString("disposition");
                    operator = rs1.getString("operator");
                    int value;
                    String SQL2 = "select * from field_form "
                    + "where application_form_id = " + applicationForm.getId() 
                    + " AND field_id = " + field_id;
                    System.out.println(SQL2);
                    rs2 = statement2.executeQuery(SQL2);
                    if (rs2 != null) 
                    {
                        if (rs2.next()) 
                        {
                            value = rs2.getInt("value");
                            if(operator.equalsIgnoreCase("g"))
                            {
                                if(value>limit)
                                {
                                    result = disposition;
                                    break;
                                }
                            }
                            else if(operator.equalsIgnoreCase("ge")) {
                                if(value>=limit)
                                {
                                    result = disposition;
                                    break;
                                }
                            }
                            else if(operator.equalsIgnoreCase("le")) {
                                if(value<=limit)
                                {
                                    result = disposition;
                                    break;
                                }
                            }
                            else if(operator.equalsIgnoreCase("l")) {
                                if(value<limit)
                                {
                                    result = disposition;
                                    break;
                                }
                            }
                        }
                    }
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
        return result;
       }
       
       
       static boolean insertChange(String applicationId,String fieldId,String value) {
        boolean saved = false;

        try {


            ConnectionManager connectionMan = new ConnectionManager();
            con = ConnectionManager.dcConnect();
            con.setAutoCommit(false);

            Statement statement = null;
            
            String SQL1 = "select * from field_form where field_id = "+fieldId+" and application_form_id = "+applicationId;
            System.out.println(SQL1);        

            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL1);
            if (rs != null) {
                if (rs.next()) {
                    String fieldFormId = rs.getString("id");
                    
                    String SQL2 = "update field_form_change set status = 'inactive' where field_form_id ="+fieldFormId;
                    System.out.println(SQL2);
                    
                    Statement statement2 = con.createStatement();
                    statement2.executeUpdate(SQL2);
                    
                    String SQL3 = "insert into field_form_change(field_form_id,value,status,change_date) "+
                            " values("+fieldFormId+",'"+value+"','active',now()') ";
                    System.out.println(SQL3);

                    Statement statement3 = con.createStatement();
                    statement3.executeUpdate(SQL3);
                }
            }
            
            con.commit();
            saved = true;

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

        return saved;
    }
}
