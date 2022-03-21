/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author Админ
 */
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    JwtTokenStore jts;
//    @Autowired
//           ClientCredentialsAccessTokenProvider ccatp;
           
//           @Autowired
//           AuthorizationCodeResourceDetails acrd;
//    @Autowired
//    OAuth2AccessTokenResponseHttpMessageConverter resp;
   
    
    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        SecurityContext sc = SecurityContextHolder.getContext();
        OAuth2AuthenticationDetails oad = (OAuth2AuthenticationDetails)sc.getAuthentication().getDetails();
        System.out.println("refresh "+jts.readAuthentication(oad.getTokenValue()));
//        System.out.println("support is "+ccatp.supportsRefresh(acrd));

//        new ClientCredentialsAccessTokenProvider();
//        OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken)sc;
//        auth.getPrincipal().getAttributes().forEach((t, u) -> {
//            System.out.println(t+" "+u);
//        });
//         OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
//auth.getAuthorizedClientRegistrationId(), auth.getName());
//        OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();
//        System.out.println(refreshToken.getExpiresAt().toString());
//        System.out.println("client is "+principal.toString());
        return principal;
    }
}
