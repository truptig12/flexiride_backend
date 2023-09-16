package com.herts.flexiride.service;

import java.util.Optional;

import com.herts.flexiride.entity.User;

public interface UserService {

	public User addUser(User lUser) throws Exception;

	public User updateUser(User lUser) throws Exception;

	public Optional<User> findByEmail(String email);

	public User findByUserId(int lUserId);

}
