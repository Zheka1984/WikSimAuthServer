package com.example;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Админ
 */
@Entity
public class UsersPojo implements Serializable {

           @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           private int id;
           @Column(unique = true)
	   private String username;
	   private String password;
           @Transient
	   private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();

    public UsersPojo() {
    }

    public UsersPojo(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }   
           
           public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<GrantedAuthority> getListOfgrantedAuthorities() {
		return listOfgrantedAuthorities;
	}
	public void setListOfgrantedAuthorities(Collection<GrantedAuthority> listOfgrantedAuthorities) {
		this.listOfgrantedAuthorities = listOfgrantedAuthorities;
	}
	

}
