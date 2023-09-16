package com.herts.flexiride.security;

import com.herts.flexiride.entity.User;

import lombok.Data;

@Data
public class JWTAuthResponse {

	private String token;

	private User user;

}
