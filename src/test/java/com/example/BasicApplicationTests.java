package com.example;

import org.junit.After;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
@ContextConfiguration(classes=BasicApplication.class)
public class BasicApplicationTests {
    @Autowired
    UsersDBQuery udbq;
    UsersPojo up = new UsersPojo();
    BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();

	@Test
	public void testInsertToBd() {
            udbq.insertUserToDb("Ann", "123");
            up = udbq.getUserDetails("Ann");
            System.out.println(up);
            assertTrue(bpe.matches("123", up.getPassword()));
	}
        @After
        public void clearTestChanges(){
            udbq.deleteByMaxId();
        }

}
