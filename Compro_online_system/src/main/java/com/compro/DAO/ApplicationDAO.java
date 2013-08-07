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
                    String fieldId = rs.getString("field_id");
                    Field field = null;
                    String SQL2 = "select * from field "
                            + "where id = " + fieldId;


                    statement = con.createStatement();
                    ResultSet rs2 = statement.executeQuery(SQL2);
                    if (rs2 != null) {
                        if (rs2.next()) {
                            field = new Field(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                                    rs.getInt("order"), rs.getString("status"), null);
                        }
                    }

                    FieldForm fieldForm = new FieldForm(rs.getInt("id"), rs.getString("value"), applicationForm, field);
                    applicationForm.getFieldsValues().put(fieldId, rs.getString("value"));
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
            String SQL = "update field_form set value = '"+fieldForm.getValue()+"' "
                    + " where id = "+fieldForm.getId();

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
}
