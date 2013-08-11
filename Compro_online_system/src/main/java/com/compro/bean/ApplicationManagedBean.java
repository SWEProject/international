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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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
    ApplicationForm applicationForm;

    private String userId;
    
    public String retrieveApplicationTemplate() {
        try {
            String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
            if(userId.equals("0"))
                userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("auserId");
            
            setUserId(userId);
            applicationTemplate = applicationService.getApplicationTemplate();
            applicationForm = applicationService.getUserApplication(userId);
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
            if(userId.equals("0"))
                userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("auserId");
            
            setUserId(userId);
            
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
                        String fieldKey = f.getId()+"";
                        if(f.getType().equals("date"))
                            fieldKey="j_idt14:_"+f.getId()+"_input";
                        String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(fieldKey);
                        
                        System.out.println(f.getName()+" ----- "+fValue);
                        FieldForm fieldForm = new FieldForm(fValue,appForm ,f);
                    }
                }
                
                applicationService.insertApplicationForm(appForm);
            }
            else
            {
                appForm.setFieldsForm(new ArrayList());
                for(int i=0;i<applicationTemplate.getSections().size();i++)
                {
                    Section sec = (Section)applicationTemplate.getSections().get(i);
                    for(int j=0;j<sec.getFields().size();j++)
                    {
                        Field f = (Field)sec.getFields().get(j);
                        String fieldKey = f.getId()+"";
                        if(f.getType().equals("date"))
                            fieldKey="j_idt14:_"+f.getId()+"_input";
                        String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(fieldKey);
                        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                        Iterator itr = parameterMap.keySet().iterator();
                        while(itr.hasNext())
                        {
                            System.out.println("wwww"+itr.next());
                        }
                        System.out.println(f.getName()+" --1-- "+fValue);
                        
                        FieldForm fieldForm = new FieldForm(fValue,appForm ,f);
                    }
                }
                
                applicationService.updateApplicationForm(appForm);
            }
            
            
                 
            
            return HOME;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String submitApplication()
    {
        try {
            applicationTemplate = applicationService.getApplicationTemplate();
            
            
            String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
            if(userId.equals("0"))
                userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("auserId");
            
            setUserId(userId);
            
            System.out.println("User ID:"+userId); 
            ApplicationForm appForm = applicationService.getUserApplication(userId);
            String dispostion = "undetermined";
            
            //There is no existing form and need to create a new one
            if(appForm == null)
            {
                appForm = new ApplicationForm("submitted", dispostion, Integer.parseInt(userId));
                
                for(int i=0;i<applicationTemplate.getSections().size();i++)
                {
                    Section sec = (Section)applicationTemplate.getSections().get(i);
                    for(int j=0;j<sec.getFields().size();j++)
                    {
                        Field f = (Field)sec.getFields().get(j);
                        String fieldKey = f.getId()+"";
                        if(f.getType().equals("date"))
                            fieldKey="j_idt14:_"+f.getId()+"_input";
                        String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(fieldKey);
                        
                        System.out.println(f.getName()+" ----- "+fValue);
                        FieldForm fieldForm = new FieldForm(fValue,appForm ,f);
                    }
                }
                dispostion = applicationService.checkRules(appForm);
                appForm.setDisposition(dispostion);
                applicationService.insertApplicationForm(appForm);
            }
            else
            {
                appForm.setFieldsForm(new ArrayList());
                appForm.setFieldsValues(new HashMap());
                appForm.setStatus("submitted");
                for(int i=0;i<applicationTemplate.getSections().size();i++)
                {
                    Section sec = (Section)applicationTemplate.getSections().get(i);
                    for(int j=0;j<sec.getFields().size();j++)
                    {
                        Field f = (Field)sec.getFields().get(j);
                        String fieldKey = f.getId()+"";
                        if(f.getType().equals("date"))
                            fieldKey="j_idt14:_"+f.getId()+"_input";
                        String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(fieldKey);
                        
                        
                        System.out.println(f.getName()+" ----- "+fValue);
                        FieldForm fieldForm = new FieldForm(fValue,appForm ,f);
                        appForm.getFieldsValues().put(f.getId(), fValue);
                    }
                }
                dispostion = applicationService.checkRules(appForm);
                appForm.setDisposition(dispostion);
                applicationService.updateApplicationForm(appForm);
            }
            
            
                 
            
            return HOME;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return ERROR;
    }
    
    
    public String changeApplication()
    {
        try {
            applicationTemplate = applicationService.getApplicationTemplate();
            
            
            String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
            if(userId.equals("0"))
                userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("auserId");
            
            setUserId(userId);
            
            System.out.println("User ID:"+userId); 
            ApplicationForm appForm = applicationService.getUserApplication(userId);
            
            for(int i=0;i<applicationTemplate.getSections().size();i++)
            {
                Section sec = (Section)applicationTemplate.getSections().get(i);
                for(int j=0;j<sec.getFields().size();j++)
                {
                    Field f = (Field)sec.getFields().get(j);
                    String fieldKey = f.getId()+"";
                    if(f.getType().equals("date"))
                        fieldKey="j_idt14:_"+f.getId()+"_input";
                    String fValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(fieldKey);
                            
                    if(!fValue.equals(appForm.getFieldsValues().get(f.getId())))
                    {
                        //need to insert new change and if there is a previous change change it to inactive
                        applicationService.insertChange(appForm.getId()+"", f.getId()+"" , fValue);
                    }
                }
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

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    
}
