/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.service;

import com.compro.model.Application;
import org.springframework.transaction.annotation.Transactional;
import com.compro.DAO.ApplicationDAO;
import com.compro.DAO.DAOFacade;
import com.compro.model.ApplicationForm;
import com.compro.model.FieldForm;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Waseem
 */
@Transactional(readOnly = true)
public class ApplicationService implements IApplicationService{
    
    public Application getApplicationTemplate()
    {
        Application appTemplate = null;
        DAOFacade facade = new DAOFacade();
        try {
            appTemplate =  facade.getApplicationTemplate();
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appTemplate;
    }
    
    public ApplicationForm getUserApplication(String userId)
    {
        DAOFacade facade = new DAOFacade();
        return facade.getUserApplication(userId);
    }
    

    public  ApplicationForm getApplicationFormFields(ApplicationForm applicationForm) {
        DAOFacade facade = new DAOFacade();
        return facade.getApplicationFormFields(applicationForm);
    }

    public boolean insertApplicationForm(ApplicationForm applicationForm) {
        DAOFacade facade = new DAOFacade();
        return facade.insertApplicationForm(applicationForm);
    }
    
    
    public boolean updateApplicationForm(ApplicationForm applicationForm) {
        DAOFacade facade = new DAOFacade();
        return facade.updateApplicationForm(applicationForm);
    }
        
        
    public boolean insertApplicationFieldForm(FieldForm fieldForm) {
        DAOFacade facade = new DAOFacade();
        return facade.insertApplicationFieldForm(fieldForm);
    }
     
       
    public boolean updateApplicationFieldForm(FieldForm fieldForm) {
        DAOFacade facade = new DAOFacade();
        return facade.updateApplicationFieldForm(fieldForm);
    }    
    
    public boolean insertChange(String applicationId,String fieldId,String value)
    {
        DAOFacade facade = new DAOFacade();
        return facade.insertChange(applicationId, fieldId, value);
    }
    
    public String checkRules(ApplicationForm applicationForm)
    {
        DAOFacade facade = new DAOFacade();
        return facade.checkRules(applicationForm);
    }
    
        public List<ApplicationForm> getAllApplication() 
    {
        DAOFacade facade = new DAOFacade();
        return facade.getAllApplication();
    }
    
    public boolean overrideDisposition(String disposition, String status, String userId)
    {
        DAOFacade facade = new DAOFacade();
        return facade.overrideDisposition(disposition, status, userId);
                
    }
}
