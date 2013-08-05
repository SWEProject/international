/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * User Entity
 * 
 * @author Netsanet
 *
 */
public class User {
       
	private int id;
	private String name;
        private String middlename;
	private String surname;
        private String email;
        private String password;

	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set User Name
	 * 
	 * @param String - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
	
        
	/**
	 * Get User Surname
	 * 
	 * @return String - User Surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Set User Surname
	 * 
	 * @param String - User Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}	

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        
	
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		
		strBuff.append(", name : ").append(getName());
		strBuff.append(", surname : ").append(getSurname());
                strBuff.append("password : ").append(getEmail());
		return strBuff.toString();
	}
}
