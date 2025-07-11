package com.fdmgroup.Retail_POD_backend.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@NotNull(message = "Username must not be blank")
	@Column(name = "Username", unique = true)
	private String username;

	@NotNull(message = "Password must not be blank")
	@Column(name = "Password")
	private String password;

	@NotNull(message = "Your Name must not be blank")
	@Column(name = "Full_Name")
	private String fullName;

	@NotNull(message = "Email address must not be blank")
	@Column(name = "Email_id", unique = true)
	private String email;

	// Future implementation
	/* private String address; */
	/*
	 * @NotNull(message = "Mobile Number must not be blank")
	 * 
	 * @Column(name = "Mobile_Number") private int mobileNumber;
	 */

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, @NotNull(message = "Username must not be blank") String username,
			@NotNull(message = "Password must not be blank") String password,
			@NotNull(message = "Your Name must not be blank") String fullName,
			@NotNull(message = "Email address must not be blank") String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullName=" + fullName
				+ ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && id == other.id
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

}
