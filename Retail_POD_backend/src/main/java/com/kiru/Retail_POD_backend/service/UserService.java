package com.fdmgroup.Retail_POD_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Retail_POD_backend.exceptions.UserNotFoundException;
import com.fdmgroup.Retail_POD_backend.model.User;
import com.fdmgroup.Retail_POD_backend.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUserByID(long userID) throws UserNotFoundException {
		Optional<User> userById = userRepository.findById(userID);
		if (!userById.isPresent()) {
			throw new UserNotFoundException("User with id " + userID + " was not found");
		}
		return userById.get();
	}

	public User createUser(User user) {

		return userRepository.save(user);

	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(long userID) {
		userRepository.deleteById(userID);

	}

}
