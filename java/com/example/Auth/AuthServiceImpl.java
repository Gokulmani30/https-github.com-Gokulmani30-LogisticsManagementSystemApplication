package com.example.Auth;


	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.stereotype.Service;

	import com.example.Entity.User;
	import com.example.Repository.*;

	@Service
	public class AuthServiceImpl implements AuthService {

	    private final UserRepository userRepository;

	    private final PasswordEncoder passwordEncoder;

	    private final AuthenticationManager authenticationManager;
	    
	    private final JwtService jwtService;

	    public AuthServiceImpl(
	            UserRepository userRepository,
	            PasswordEncoder passwordEncoder,
	            AuthenticationManager authenticationManager,
	            JwtService jwtService) {

	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.authenticationManager = authenticationManager;
	        this.jwtService = jwtService;
	    }

	    @Override
	    public AuthResponse register(RegisterRequest request) {

	        if (userRepository.findByUsername(request.getUsername()).isPresent()) {

	            throw new RuntimeException("Username already exists");
	        }

	        User user = new User();

	        user.setUsername(request.getUsername());

	        // Encrypt password
	        user.setPassword(
	                passwordEncoder.encode(request.getPassword())
	        );

	        if (request.getRole() == null || request.getRole().isBlank()) {
	            user.setRole("USER");
	        } else {
	            user.setRole(request.getRole());
	        }

	        userRepository.save(user);

	        String token = jwtService.generateToken(user.getUsername());
	        return new AuthResponse(
	                user.getUsername(),
	                user.getRole(),
	                "User Registration successfully...",
	                token
	               
	        );
	    }

	    @Override
	    public AuthResponse login(LoginRequest request) {

	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getUsername(),
	                        request.getPassword()
	                )
	        );

	        User user = userRepository
	                .findByUsername(request.getUsername())
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	        String token = jwtService.generateToken(user.getUsername());
	        return new AuthResponse(
	                user.getUsername(),
	                user.getRole(),
	                "Login successfully!!!",
	                token
	        );
	    }

}