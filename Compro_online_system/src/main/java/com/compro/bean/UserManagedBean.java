/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.bean;

import com.compro.model.User;
import com.compro.service.IUserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Netsanet
 */
@ManagedBean(name="userMB")
@SessionScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	private static final String HOME = "home";
        private static final String REGISTER = "register";
        private static final String INDEX = "index";
        private static final String ACCOUNT = "account";
        private static final String CHANGEAPPLICATIONDISPOSITION = "ChangeApplicationDisposition";
        
        
	//Spring User Service is injected...
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
	
	List<User> userList;
	private String message;
	private int id;
	private String name;
        private String middlename;
	private String surname;
	private String email;
        private String password;
        
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	public String addUser() {
		try {
			User user = new User();
			
			user.setName(getName());
                        user.setMiddlename(getMiddlename());
			user.setSurname(getSurname());
                        user.setEmail(getEmail());
			getUserService().addUser(user);
			return INDEX;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		 setMessage("register");
		return ERROR;
	}
	
        public String loginUser()
        {
            try {
			User user = new User();
			
			user.setPassword(getPassword());
                        user.setEmail(getEmail());
                        
			User u = getUserService().loginUser(user);
                       
                        
			if(u == null)
                        { 
                            setMessage("login");
                            return ERROR;
                        }
                        else{   
                            setId(u.getId());
                            if(u.getRole().equals("applicant"))
                                return HOME;
                            else if(u.getRole().equals("admission"))
                               return CHANGEAPPLICATIONDISPOSITION;
                        }
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		setMessage("login");
		return ERROR;
        }
        
        public String redirectToRegister()
        {
          	
		return REGISTER;
        }
        
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setEmail("");
		this.setName("");
                this.setMiddlename("");
		this.setSurname("");
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		//userList.addAll(getUserService().getUsers());
		return userList;
	}
	
       public String getAccount(){
           User user=getUserService().getAccount(getId());
           if(user!=null){
              setId(user.getId());
               setEmail(user.getEmail());
               setMiddlename(user.getMiddlename());
               setName(user.getName());
               setSurname(user.getSurname());
           }
           return ACCOUNT;
       }
       
       public String changePassword(){
           
            boolean ret= getUserService().changePassword(getId());
             setMessage("change password");
            if(ret)
               return SUCCESS;
           
            else
                return ERROR;
            
       }
       
       public String forgotPassword(){
           
          boolean ret= getUserService().forgotPassword(getEmail());
           setMessage("forgot password");
            if(ret)
             return SUCCESS;
            else
              return ERROR;
       }
	/**
	 * Get User Service
	 * 
	 * @return IUserService - User Service
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * Set User Service
	 * 
	 * @param IUserService - User Service
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Set User List
	 * 
	 * @param List - User List
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
