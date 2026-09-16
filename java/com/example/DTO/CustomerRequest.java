package com.example.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
 
	@NotBlank(message = "Customer name is required")
	private String customerName;
	
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
	private String email;
	
	 @NotBlank(message = "Phone number is required")
	    @Pattern(
	        regexp = "[6-9][0-9]{9}",
	        message = "Phone number must contain 10 digits")
	private String phoneNumber;
	
	@NotBlank(message = "Address is required")
	private String address;
}
