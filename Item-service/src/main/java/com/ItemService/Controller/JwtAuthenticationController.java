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
	        authenticate(req.getUsername(), req.getPassword());
	        final UserDetails userDetails = userDetailsService
	                                .loadUserByUsername(req.getUsername());
	        final String token = jwtTokenUtil.generateToken(userDetails);
	        final User userData=userService.findUser(req.getUsername()).get();
	        final boolean adminUser = userData.getRoles().equalsIgnoreCase("ADMIN")?true:false;
	        JwtResponse jr=new JwtResponse(token);
	        jr.setUser(userData);
	        jr.setAdminUser(adminUser);
	        return ResponseEntity.ok(jr);
	    }

	    private void authenticate(String username, String password) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(username, password));
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
	    }
}
    
    
    
