/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.service;

import com.compro.model.Application;
import com.compro.model.ApplicationForm;
import com.compro.model.FieldForm;

/**
 *
 * @author Waseem
 */
public interface IApplicationService {
    
    public Application getApplicationTemplate();
    
    public ApplicationForm getUserApplication(String userId);
    

    public  ApplicationForm getApplicationFormFields(ApplicationForm applicationForm);

    public boolean insertApplicationForm(ApplicationForm applicationForm);
    
    
    public boolean updateApplicationForm(ApplicationForm applicationForm);
        
        
    public boolean insertApplicationFieldForm(FieldForm fieldForm);
       
    public boolean updateApplicationFieldForm(FieldForm fieldForm);
    
    public boolean insertChange(String applicationId,String fieldId,String value);
    
    public String checkRules(ApplicationForm applicationForm);
}
