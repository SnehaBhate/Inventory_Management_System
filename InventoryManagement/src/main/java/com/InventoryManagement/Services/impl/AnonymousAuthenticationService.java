//anonymous authentication allows users to access certain resources without requiring them to authenticate or provide credentials.
package com.InventoryManagement.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AnonymousAuthenticationService {
   
	@Autowired
    private final UserDetailsService userDetailsService;
    
	//constructor
	@Autowired
    public AnonymousAuthenticationService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setAnonymousAuthentication() {
        
    	// Load the anonymous user details
        UserDetails anonymousUserDetails = userDetailsService.loadUserByUsername("anonymousUser");

        // Create an anonymous authentication token
        Authentication anonymousAuthentication = new AnonymousAuthenticationToken(
                "anonymousUser", anonymousUserDetails, anonymousUserDetails.getAuthorities());

        // Set the anonymous authentication in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(anonymousAuthentication);
    }
}