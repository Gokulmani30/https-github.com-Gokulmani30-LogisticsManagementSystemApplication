package com.example.Auth;

public interface AuthService {

	AuthResponse register(RegisterRequest request);
	
	AuthResponse login(LoginRequest request);
}
