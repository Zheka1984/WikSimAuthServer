/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.security.core.userdetails.User;



/**
 *
 * @author Админ
 */
public class UsersHelper extends User{

	private static final long serialVersionUID = 1L;
	   public UsersHelper(UsersPojo user) {
	      super(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  user.getListOfgrantedAuthorities()
	    		);
	   }
}
