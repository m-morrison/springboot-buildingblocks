package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This model is to create a user")
@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname", "lastname"}) -- Static Filtering JsonIgnore
@JsonFilter(value = "userFilter")
public class User extends RepresentationModel<User> {
	
	@ApiModelProperty(notes = "Auto generated unique id", required = true, position = 1)
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long userId;
	
	@ApiModelProperty(notes = "username should be flname", example = "mmorrison", required = true, position = 2)
	@NotEmpty(message = "Username is a mandatory field.  Please provide username.")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;
	
	@Size(min = 2, message = "First Name should have at least 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String firstname;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;
	
	@Column(name = "EMAIL", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	@JsonView(Views.Internal.class)
	//@JsonIgnore -- Static Filtering
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name = "ADDRESS")
	private String address;

	//No Argument Constructor
	public User() { }
	
	//Fields Constructor
	public User(Long userId, String username, String firstname, String lastname, String email, String role, String ssn, String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.address = address;
	}
	
	//Getters and Setters

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//To String - Required for Bean Logging, otherwise Optional
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders
				+ ", address=" + address + "]";
	}
	
}
