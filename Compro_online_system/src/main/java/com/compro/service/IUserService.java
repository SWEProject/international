/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compro.service;

import com.compro.model.User;
import java.util.List;

/**
 *
 * @author Nesanet
 */
public interface IUserService {
    /**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addUser(User user);
	public User loginUser(User user);
        public User getAccount(int id);
        public boolean changePassword(int id);
        public boolean forgotPassword(String email);
        
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	//public void updateUser(User user);

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	//public void deleteUser(User user);
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	//public User getUserById(int id);
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	//public List<User> getUsers();
}
