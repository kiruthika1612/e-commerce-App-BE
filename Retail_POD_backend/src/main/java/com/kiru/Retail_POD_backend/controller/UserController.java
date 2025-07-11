package com.fdmgroup.Retail_POD_backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.Retail_POD_backend.exceptions.UserNotFoundException;
import com.fdmgroup.Retail_POD_backend.model.User;
import com.fdmgroup.Retail_POD_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@Operation(summary = "Register a new user", description = "Register a new user into the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "New User successfully registered") })
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User createdUser = userService.createUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		// return ResponseEntity.created(location).build();
		return ResponseEntity.created(location).body(createdUser);

	}

	@Operation(summary = "Get a user", description = "Get a user information by user Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User info successfully retrieved"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) throws UserNotFoundException {
		User user = userService.getUserByID(id);

		return ResponseEntity.ok(user);
	}

	@Operation(summary = "Get all registered Users")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All registered Users") })
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		// System.out.println("reaching controller");
		List<User> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}

	@Operation(summary = "Update a user", description = "Update a user data in the database ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User Data successfully updated"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
		User updateUser = userService.updateUser(user);
		return ResponseEntity.ok(updateUser);
	}

	@Operation(summary = "Delete User for given id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User deleted successfully"),
			@ApiResponse(responseCode = "404", description = "User specified is not available for deletion") })
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}

}
