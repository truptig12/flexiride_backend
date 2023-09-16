package com.herts.flexiride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.herts.flexiride.security.JWTAuthRequest;
import com.herts.flexiride.security.JWTAuthResponse;
import com.herts.flexiride.security.JWTTokenHelper;
import com.herts.flexiride.service.UserService;

@RestController
@RequestMapping("/")
public class AuthController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserService lUserService;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request) {

		try {
			this.authentication(request.getUsername(), request.getPassword());
		} catch (Exception e) {
			JWTAuthResponse response = new JWTAuthResponse();
			return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.UNAUTHORIZED);
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JWTAuthResponse response = new JWTAuthResponse();

		response.setUser(lUserService.findByEmail(request.getUsername()).get());

		response.setToken(token);

		return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);

	}

	private void authentication(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalide Details !!");
			throw new Exception("Invalid Username or Password");
		}

	}

}
