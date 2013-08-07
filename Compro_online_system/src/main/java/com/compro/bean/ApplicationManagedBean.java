/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.bean;

import com.compro.model.Application;
import com.compro.model.ApplicationForm;
import com.compro.model.Field;
import com.compro.model.FieldForm;
import com.compro.model.Section;
import com.compro.model.User;
import com.compro.service.IApplicationService;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Waseem
 */
@ManagedBean(name = "applicationMB")
@RequestScoped
public class ApplicationManagedBean implements Serializable {

    private static final String ERROR = "error";
    private static final String APPLICATION = "application";
    private static final String HOME = "home";
    
    @ManagedProperty(value = "#{ApplicationService}")
    IApplicationService applicationService;
    Application applicationTemplate;
    //@ManagedProperty("#{param.id}")
    private int id;

    public String retrieveApplicationTemplate() {
        try {
            applicationTemplate = applicationService.getApplicationTemplate();
            
            return APPLICATION;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }
    
    public String saveApplication()
    {
        try {
            applicationTemplate = applicationService.getApplicationTemplate();
            
            
            String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
            System.out.println("User ID:"+userId); 
            ApplicationForm appForm = applicationService.getUserApplication(userId);
            
            //There is no existing form and need to create a new one
            if(appForm == null)
            {
                appForm = new ApplicationForm("active", "undetermined", Integer.parseInt(userId));
                
                for(int i=0;i<applicationTemplate.getSections().size();i++)
                {
                    Section sec = (Section)applicationTemplate.getSections().get(i);
                    for(int j=0;j<sec.getFields().size();j++)
                    {
                        Field f = (Field)sec.getFields().get(j);
                        String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(f.getId());
                        System.out.println(f.getName()+" ----- "+fValue);
                        FieldForm fieldForm = new FieldForm(fValue,appForm ,f);
                    }
                }
                
                applicationService.insertApplicationForm(appForm);
            }
            else
            {

            }
            
            
                 
            
            return HOME;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public IApplicationService getApplicationService() {
        return applicationService;
    }

    public void setApplicationService(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public Application getApplicationTemplate() {
        return applicationTemplate;
    }

    public void setApplicationTemplate(Application applicationTemplate) {
        this.applicationTemplate = applicationTemplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    
}