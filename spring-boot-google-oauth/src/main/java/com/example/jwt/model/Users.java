package com.example.jwt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Users {

	@Id
	private String sub;

	@Email
	private String email;
	private String name;

	@Size(max = 10)
	private String customerPhone;

	@Size(max = 255)
	private String customerAddress;

}
