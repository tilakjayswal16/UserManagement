package com.bitbuy.usermanagement.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "username","password","user","email","phone"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String uuid;

	@NotBlank(message = "Please enter the Username")
	@NotNull(message = "Please enter the Username")
	private String username;

	@NotBlank(message = "Please enter the Password")
	@NotNull(message = "Please enter the Password")
	private String password;

	private String name;

	@Email(message = "Email should be valid")
	private String email;

	private long phone;

	public User(){}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

}
