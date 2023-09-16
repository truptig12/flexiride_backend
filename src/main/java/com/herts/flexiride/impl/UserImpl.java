package com.herts.flexiride.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.flexiride.entity.User;
import com.herts.flexiride.repository.UserRepo;
import com.herts.flexiride.service.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	UserRepo lUserRepo;

	@Override
	public User addUser(User lUser) throws Exception {
		Optional<User> lUserEntity = findByEmail(lUser.getEmail());
		System.out.println("Same Email User Entity : " + lUserEntity);

		if (lUserEntity == null) {
			throw new Exception("User alredy exist");
		}
		return this.lUserRepo.save(lUser);
	}

	@Override
	public User updateUser(User lUser) throws Exception {

		Optional<User> lUserEntity = findByEmail(lUser.getEmail());
		System.out.println("Same Email User Entity : " + lUserEntity);

		if (lUserEntity != null) {
			if (lUserEntity.get().getUserId() != lUser.getUserId())
				throw new Exception("Email id alredy exist");
		}

		try {
			User lUserToUpdate = this.lUserRepo.getById(lUser.getUserId());
			System.out.println("User To Update : " + lUserToUpdate.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("User data not exist");
		}
		return this.lUserRepo.save(lUser);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return this.lUserRepo.findByEmail(email);
	}

	@Override
	public User findByUserId(int lUserId) {
		User lUser = null;
		try {
			lUser = this.lUserRepo.findById(lUserId).get();
		} catch (Exception e) {
			return null;
		}
		return lUser;
	}
}
