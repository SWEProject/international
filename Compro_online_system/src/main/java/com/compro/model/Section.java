/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Waseem
 */
public class Section {
    private int id;
    private String name;
    private String status;
    private int order;
    private Application application;
    private List<Field> fields;

    public Section() {
    }
    
    public Section(int id,String name, String status,int order, Application application) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.order = order;
        this.application = application;
        this.application.addSection(this);
        fields = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    
    public void addField(Field field)
    {
        fields.add(field);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}
