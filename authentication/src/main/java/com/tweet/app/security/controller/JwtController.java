package com.tweet.app.security.controller;

import com.tweet.app.security.service.UserDetailsServiceImpl;
import com.tweet.app.security.helper.JwtUtils;
import com.tweet.app.security.model.JwtAuthRequest;
import com.tweet.app.security.model.JwtAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                                                                    jwtAuthRequest.getUsername(),
                                                                                     jwtAuthRequest.getPassword()));
        } catch(UsernameNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad Credentials");
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new JwtAuthResponse("Bad Credentials"));
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
}
