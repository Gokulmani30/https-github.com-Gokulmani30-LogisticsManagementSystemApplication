package com.example.Auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	
	private String username;
	
	private String role;
	
	private String message;
	
	private String token;
	
}
