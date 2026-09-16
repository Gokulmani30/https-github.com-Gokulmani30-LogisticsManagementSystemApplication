package com.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerid")
	private Long customerId;
	
	@NotBlank(message = "Customer name is required")
	private String customerName;
	
	@Email(message = "Enter a vaild E-mail")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "[6-9][0-9]{9}", 
	message = "PhoneNumber must contains 10 digits")
	
	private String phoneNumber;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Version
	private Long version;
	
	@OneToMany(mappedBy = "customer")
	private List<Shipment> shipments;
	
}
