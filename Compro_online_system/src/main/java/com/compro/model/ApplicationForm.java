/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Waseem
 */
public class ApplicationForm {
    private int id;
    private String status;
    private String disposition;
    private Date creationDate;
    private Date lastModifiedDate;
    private int userId;
    private List<FieldForm> fieldsForm  = new ArrayList();

    public ApplicationForm() {
    }
    
    
    
    public ApplicationForm(int id,String status, String disposition, Date creationDate, Date lastModifiedDate,int userId) {
        this.id = id;
        this.status = status;
        this.disposition = disposition;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.userId = userId;
    }

    public ApplicationForm(String status, String disposition, int userId) {
        this.status = status;
        this.disposition = disposition;
        this.userId = userId;
    }

    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FieldForm> getFieldsForm() {
        return fieldsForm;
    }

    public void setFieldsForm(List<FieldForm> fieldsForm) {
        this.fieldsForm = fieldsForm;
    }
    
    public void addFieldForm(FieldForm field)
    {
        fieldsForm.add(field);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
