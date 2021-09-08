package com.bitbuy.usermanagement.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","uuid","user","email","phone"})
public class UserUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String uuid;

	@NotBlank(message = "Please enter the name")
	@NotNull(message = "Please enter the name")
	private String name;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Please enter the email")
	@NotNull(message = "Please enter the email")
	private String email;

	private long phone;

	public UserUpdate(){}

	public UserUpdate(String name, String email, long phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	@Override
	public String toString() {
		return "UserUpdate{" +
				"id=" + id +
				", uuid='" + uuid + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", phone=" + phone +
				'}';
	}
}
