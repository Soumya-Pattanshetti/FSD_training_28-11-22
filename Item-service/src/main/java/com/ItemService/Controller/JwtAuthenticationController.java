package com.ItemService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ItemService.Entity.User;
import com.ItemService.model.JwtRequest;
import com.ItemService.model.JwtResponse;
import com.ItemService.service.JwtUserDetailsService;
import com.ItemService.service.UserServices;
import com.ItemService.util.JwtTokenUtil;


@RestController
@CrossOrigin
public class JwtAuthenticationController {
	 @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	    @Autowired
	    private JwtUserDetailsService userDetailsService;
	    
	    @Autowired
		UserServices userService;
	    
	    @CrossOrigin(value = "http://localhost:4200")
	    @PostMapping("/authenticate")
	    public ResponseEntity<?> authenticate(@RequestBody JwtRequest req) throws Exception {
	        authenticate(req.getEmail(), req.getPassword());
	        final UserDetails userDetails = userDetailsService
	                                .loadUserByUsername(req.getEmail());
	        System.out.println("username "+req.getEmail());
	        //final String token = jwtTokenUtil.generateToken(userDetails);
	        //final User userData=userService.findUserByEmail(req.getEmail()).get();
	       // final boolean adminUser = userData.getRoles().equalsIgnoreCase("ADMIN")?true:false;
	        //JwtResponse jr=new JwtResponse(token);
	        //jr.setUser(userData);
	        //jr.setAdminUser(adminUser);
	        //return ResponseEntity.ok();
	        return ResponseEntity.ok(req);
	    	
	    }

	    private void authenticate(String email, String password) throws Exception {
	        try {
	        	System.out.println("username"+email);
	        	System.out.println(password);
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(email, password));
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
	    }
	    
}
    
    
    
