/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.bean;

import com.compro.model.Application;
import com.compro.model.ApplicationForm;
import com.compro.model.Section;
import com.compro.service.IApplicationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
    
    
    
    @PostConstruct
    public void init() 
    {

        application = new ArrayList<ApplicationForm>();
        application = getApplicationService().getAllApplication();
    }
    
    public String submitApplication(String disposition, String status)
    {
        boolean result = false;
        
        result = applicationService.overrideDisposition(disposition, status, this.userId);
        
        application = new ArrayList<ApplicationForm>();
        application = getApplicationService().getAllApplication();
        
        if(result)
            return CHANGEAPPLICATIONDISPOSITION;
        
        
            return ERROR;
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
                    