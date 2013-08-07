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
public class Application {
    private int id;
    private String status;
    private String disposition;
    private Date creationDate;
    private Date lastModifiedDate;
    private List<Section> sections  = new ArrayList();

    public Application() {
    }
    
    
    
    public Application(int id,String status, String disposition, Date creationDate, Date lastModifiedDate) {
        this.id = id;
        this.status = status;
        this.disposition = disposition;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
    
    public void addSection(Section section)
    {
        sections.add(section);
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
