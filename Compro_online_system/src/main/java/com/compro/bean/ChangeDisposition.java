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
import com.compro.service.IApplicationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;


@ManagedBean(name="tableBean")
@SessionScoped
public class ChangeDisposition implements Serializable {
    
    @ManagedProperty(value = "#{ApplicationService}")
    static IApplicationService applicationService;
    List<ApplicationForm> application;
    private Application applicationTemplate;
    ApplicationForm applicationForm;
    private String userId;
    private String disposition;
    private String status;
    
    private static final String ERROR = "error";
    private static final String APPLICATIONWITHDISPOSITION = "ApplicationWithDisposition";
    private static final String CHANGEAPPLICATIONDISPOSITION = "ChangeApplicationDisposition";
    private static final String HOME = "home";
    private static final String SUCCESS = "success";
    private static final String SEARCHAPPLICATION = "SearchApplication";
    
    
    @PostConstruct
    public void init() 
    {

        application = new ArrayList<ApplicationForm>();
        application = getApplicationService().getAllApplication();
    }
    
    public String submitApplication(String disposition, String status)
    {
        //result = applicationService.overrideDisposition(disposition, status, this.userId);
        
        ApplicationForm appForm = applicationService.getUserApplication(this.userId);
        appForm.setDisposition(disposition);
        appForm.setStatus(status);
        appForm.setFieldsForm(new ArrayList());
        
        for(int i=0;i<applicationTemplate.getSections().size();i++)
        {
            Section sec = (Section)applicationTemplate.getSections().get(i);
            for(int j=0;j<sec.getFields().size();j++)
            {
                Field f = (Field)sec.getFields().get(j);
                String fieldKey = f.getId()+"";
                String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(fieldKey);
                System.out.println(f.getName()+" --1-- "+fValue);

                FieldForm fieldForm = new FieldForm(fValue,appForm ,f);
            }
        }

        applicationService.updateApplicationForm(appForm);
        
        application = new ArrayList<ApplicationForm>();
        application = getApplicationService().getAllApplication();
        applicationForm = applicationService.getUserApplication(this.userId);
        
        return CHANGEAPPLICATIONDISPOSITION;
        
    }
    
    
    public String comeHere(String id)
    {
        try 
        {
            setUserId(id);
            
            applicationTemplate = applicationService.getApplicationTemplate();
            applicationForm = applicationService.getUserApplication(id);
            
            setDisposition(applicationForm.getDisposition());
            setStatus(applicationForm.getStatus());
            
            return APPLICATIONWITHDISPOSITION;
        } 
        catch (DataAccessException e) 
        {
            e.printStackTrace();
        }

        return ERROR;
    }
        
     public String searchApplication(String userId)
    {
        try 
        {
            
            setUserId(userId);
            
            applicationTemplate = applicationService.getApplicationTemplate();
            applicationForm = applicationService.getUserApplication(userId);
            
            if(applicationTemplate == null || applicationForm == null)
            {
                return ERROR;
            }
            
            setDisposition(applicationForm.getDisposition());
            setStatus(applicationForm.getStatus());
            
            return SEARCHAPPLICATION;
        } 
        catch (DataAccessException e) 
        {
            e.printStackTrace();
        }

        return ERROR;
    }
    
      /**
     * @return the application
     */
    public List<ApplicationForm> getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(List<ApplicationForm> application) {
        this.application = application;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the applicationService
     */
    public IApplicationService getApplicationService() {
        return applicationService;
    }

    /**
     * @param applicationService the applicationService to set
     */
    public void setApplicationService(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * @return the disposition
     */
    public String getDisposition() {
        return disposition;
    }

    /**
     * @param disposition the disposition to set
     */
    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    /**
     * @return the applicationTemplate
     */
    public Application getApplicationTemplate() {
        return applicationTemplate;
    }

    /**
     * @param applicationTemplate the applicationTemplate to set
     */
    public void setApplicationTemplate(Application applicationTemplate) {
        this.applicationTemplate = applicationTemplate;
    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    
    
    
}
                    