/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.DAO;

import com.compro.model.Application;
import com.compro.model.ApplicationForm;
import com.compro.model.FieldForm;
import java.sql.SQLException;
import com.compro.model.User;import java.util.List;
;

/**
 *
 * @author asingh
 */
public class DAOFacade {
      
    AdmissionStaffDAO admissionStaffDAO = new AdmissionStaffDAO();
    
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
   
    public Application getApplicationTemplate() throws SQLException
    {
        return ApplicationDAO.getApplicationTemplate();
    }
    
    public  ApplicationForm getUserApplication(String userId) {
        return ApplicationDAO.getUserApplication(userId);
    }

    public  ApplicationForm getApplicationFormFields(ApplicationForm applicationForm) {
        return ApplicationDAO.getApplicationFormFields(applicationForm);
    }

    public boolean insertApplicationForm(ApplicationForm applicationForm) {
        return ApplicationDAO.insertApplicationForm(applicationForm);
    }
    
    
    public boolean updateApplicationForm(ApplicationForm applicationForm) {
        return ApplicationDAO.updateApplicationForm(applicationForm);
    }
        
        
    public boolean insertApplicationFieldForm(FieldForm fieldForm) {
        return ApplicationDAO.insertApplicationFieldForm(fieldForm);
    }
       
       
    public boolean updateApplicationFieldForm(FieldForm fieldForm) {
        return ApplicationDAO.updateApplicationFieldForm(fieldForm);
    } 
    
    public boolean insertChange(String applicationId,String fieldId,String value)
    {
        return ApplicationDAO.insertChange(applicationId, fieldId, value);
    }
    
    public String checkRules(ApplicationForm applicationForm)
    {
        return ApplicationDAO.checkRules( applicationForm);
    }
    
        public User getAccount(int id){
       return UserDAO.getAccount(id);
    }
    
    public boolean changePassword(User user){
        return UserDAO.changePassword(user);
       
    }
    
    public User getUserByEamil(String email){
        return UserDAO.getUserByEamil(email);
    }
    
        public List<ApplicationForm> getAllApplication() 
    {
        return admissionStaffDAO.getAllApplication();
    }
    
    public boolean overrideDisposition(String disposition, String status, String userId)
    {
        
        return admissionStaffDAO.overrideDisposition(disposition, status, userId);
    }
}
