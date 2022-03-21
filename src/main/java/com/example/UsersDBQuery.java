/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.example.SecurityConfiguration;

/**
 *
 * @author Админ
 */
@Repository
public class UsersDBQuery {
    
@Autowired
JdbcTemplate jdbcTemplate;
	 
         BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
             
	   public UsersPojo getUserDetails(String username) {
               
	      Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
	      String userSQLQuery = "SELECT * FROM users_pojo WHERE USERNAME='"+username+"';";
	      List<UsersPojo> list = jdbcTemplate.query(userSQLQuery, new BeanPropertyRowMapper(UsersPojo.class));
	      if (list.size() > 0) {
	         GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
	         listOfgrantedAuthorities.add(grantedAuthority);
	         list.get(0).setListOfgrantedAuthorities(listOfgrantedAuthorities);
	         return list.get(0);
	      }
               System.out.println(list.size());
	      return null;
	   }
           public void insertUserToDb(String username, String password){
               System.out.println(jdbcTemplate);
               password = bpe.encode(password);
               String sql = "Insert into users_pojo (password, username) VALUES ('"+password+"', '"+username+"');";
               jdbcTemplate.execute(sql);
           }
           public void deleteByMaxId(){
               String sql = "Delete from users_pojo where id=(Select Max(id)from users_pojo);";
               jdbcTemplate.execute(sql);
           }
}
