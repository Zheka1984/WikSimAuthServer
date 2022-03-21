package com.example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
"MIICXQIBAAKBgQC8HhmQk0sJc3cuckf9a6S+d/lLfH4gvyT+r0RsY5kyho5YVfUs\n" +
"V/VYuBg9f4QXOzVFkEhkYt1eaY8a4z3XdmyiG8pU3XHAib57tRIxPotO1J2SxZl7\n" +
"wVqecg6ai+P45QQHR/xC26ujvPRRaiff4mliIbPWrUHWZSlXGTLjtWRXqQIDAQAB\n" +
"AoGBAKxgVIKzRZ4KbRplY+wm+BYEb66pDBZLsMWjquo7NcUjoUn2siuJCeva/XNU\n" +
"5qA3A/CTbpJ2OWzrA1PbCO7nuFAdPlp6mU5CybpqCgqT+gDBxeJYjzwfRtJxy0G6\n" +
"3cA2xhPB6k6uWhqemACwSdVQczh1TE0WyxQq2MGe31jwnBkBAkEA3ZT6Lf7hqb3a\n" +
"YbVWoAm731u6QW+cn7NRmP+pJ2wF6dHHWlg/DUiOJQ7QrgWwj+eeDC7/tIpGtZ1s\n" +
"ZEv0qZ8eoQJBANlWcJadLR2rbTz9M0vD2KPvdMeP0xLkLgu5Zr227/twIuGDMB/x\n" +
"B9I6NbPMHlrQIZnzgvMJw1F8aAmbJym5xAkCQARnzN2TPrtYOfpNV66Q4vpa4YYh\n" +
"iPSn5QxssNlGzYtLj3IdZQuHkMPt2npLRKpnyX2mhZD6WtOLkLchx3iD4GECQQCW\n" +
"908fn7AeAtzPV4XRJAoQ3Z09na7dWXiOlktCjpcmDZ99DBZaqZ9oLRzudmPDav1c\n" +
"JeUZEvgEWykdOxO6DRqZAkAHGGW/+zy5A3YfQhGvIQe8Fk4FC67TnrwyOi9RLIxn\n" +
"nB57tWzQSy3U1luLy1eiRxOcMb+IVn1Ne5i1E+uy5ZcS\n" +
"-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8HhmQk0sJc3cuckf9a6S+d/lL\n" +
"fH4gvyT+r0RsY5kyho5YVfUsV/VYuBg9f4QXOzVFkEhkYt1eaY8a4z3XdmyiG8pU\n" +
"3XHAib57tRIxPotO1J2SxZl7wVqecg6ai+P45QQHR/xC26ujvPRRaiff4mliIbPW\n" +
"rUHWZSlXGTLjtWRXqQIDAQAB\n" +
"-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
               System.out.println(privateKey.getBytes().length);
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId)
                      .secret(passwordEncoder
                      .encode(clientSecret))
                      .scopes("read", "write")
	              .authorizedGrantTypes("authorization_code", "refresh_token")
                      .accessTokenValiditySeconds(0)
	              .refreshTokenValiditySeconds(20)
                      .autoApprove(true)
                      .redirectUris("http://localhost:8082/ui-one/login",
                              "http://localhost:8083/ui2/login","http://localhost:8082/login",
                              "http://www.example.com/", "http://localhost:8082/ui-one/find", 
                              "http://localhost:8082/ui-one/add");
	   }

}
