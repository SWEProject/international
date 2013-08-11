/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.service;

import com.compro.DAO.DAOFacade;
import com.compro.model.User;
import com.compro.utilities.PasswordGenerator;
import com.compro.utilities.SendMail;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nesanet
 */
@Transactional(readOnly = true)
public class UserService implements IUserService {

	// UseDAOFacade is injected...
	DAOFacade facadeDAO;

        public void setFacadeDAO(DAOFacade facadeDAO) {
            this.facadeDAO = facadeDAO;
        }
	
        
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) {
            
            String p = PasswordGenerator.getPassword(6);
            user.setPassword(p);
            //DAOFacade facade = new DAOFacade();
            try {            
                		//getUserDAO().addUser(user);
            boolean val=facadeDAO.registration(user);
            if(val){
                 SendMail sendMail=new SendMail("cs425.intl@gamil.com", user.getEmail(),"Mum application password","Dear "+user.getName()+"\n please use the below password to login.\n"+p);
                 sendMail.send();
            }
               
            
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        @Transactional(readOnly = false)
        @Override
	public User loginUser(User user) {
            //DAOFacade facade = new DAOFacade();
            try {            
                		//getUserDAO().addUser(user);
            user = facadeDAO.login(user.getEmail(),user.getPassword());
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return user;
        }
        
        @Transactional(readOnly = false)
        
        public User getAccount(int id){
            User user=new User();
            try{
                user=facadeDAO.getAccount(id);
             }catch (Exception ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            return user;
        }
        
        @Transactional(readOnly = false)
        public boolean changePassword(int id){
            
            try{
                String p = PasswordGenerator.getPassword(6);
                boolean val=facadeDAO.changePassword(p, id);
                if(val){
                     User user=new User();
                    user=facadeDAO.getAccount(id);
                  SendMail sendMail=new SendMail("cs425.intl@gamil.com", user.getEmail(),"Mum application password","Dear "+user.getName()+"\n please use the below password to login.\n"+p);
                 sendMail.send();  
                }
                return val;
            }catch (Exception ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return false;
        }
        
        public boolean forgotPassword(String email){
           
            try{
               User user=facadeDAO.getUserByEamil(email);
                if(user!=null){
                  return changePassword(user.getId()); 
                }
             }catch (Exception ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
//        public User getUserByEamil(String email){
//             User user=new User();
//            try{
//                user=facadeDAO.getUserByEamil(email);
//             }catch (Exception ex) {
//                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//            }   
//            
//            return user;
//        }
}
