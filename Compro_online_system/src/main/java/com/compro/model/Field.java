/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.model;

/**
 *
 * @author Waseem
 */
public class Field {
    private int id;
    private String name;
    private String type;
    private String value;
    private String status;
    private int order;
    private Section section;

    public Field() {
    }

    
    public Field(int id,String name, String type,int order,String status, Section section) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.order = order;
        this.status = status;
        this.section = section;
        this.section.addField(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
