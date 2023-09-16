package com.herts.flexiride.security;

import lombok.Data;

@Data
public class JWTAuthRequest {

	private String username;

	private String password;
}
