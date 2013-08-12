/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.model;

/**
 *
 * @author Waseem
 */
public class FieldForm {
    
    private int id;
    private String value;
    private ApplicationForm applicationForm;
    private Field field;

    public FieldForm(int id, String value, ApplicationForm applicationForm, Field field) {
        this.id = id;
        this.value = value;
        this.applicationForm = applicationForm;
        this.field = field;
        
        this.applicationForm.addFieldForm(this);
    }

    public FieldForm(String value, ApplicationForm applicationForm, Field field) {
        this.value = value;
        this.applicationForm = applicationForm;
        this.field = field;
        
        this.applicationForm.addFieldForm(this);
        
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
    
}
