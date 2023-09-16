package com.herts.flexiride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.herts.flexiride.entity.ResponseEntity;
import com.herts.flexiride.entity.User;
import com.herts.flexiride.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService lUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addUser(@RequestBody User lUser) {
		User lUserEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			lUser.setPassword(this.passwordEncoder.encode(lUser.getPassword()));
			lUserEntity = lUserService.addUser(lUser);
			System.out.println(lUserEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription(lUserEntity.getFirstName() + "'s details added successfully");
			lResponseEntity.setId(lUserEntity.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateUser(@RequestBody User lUser) {
		User lUserEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(lUser.toString());
			if (lUser.getUserId() == 0) {
				throw new Exception("Invalid UserId");
			}
			lUserEntity = lUserService.updateUser(lUser);
			System.out.println(lUserEntity.toString());
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setId(lUserEntity.getUserId());
		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getUserByEmail(@RequestParam(value = "email", required = false) String lEmail) {
		User lUserEntity;
		try {
			lUserEntity = lUserService.findByEmail(lEmail).get();
			if (lUserEntity == null) {
				lUserEntity = new User();
				lUserEntity.setResponseCode(401);
				lUserEntity.setResponseDescription("User Not Found!");
			} else {
				lUserEntity.setResponseCode(200);
				lUserEntity.setResponseDescription("success");
				lUserEntity.setId(lUserEntity.getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			lUserEntity = new User();
			lUserEntity.setResponseCode(401);
			lUserEntity.setResponseDescription(e.getMessage());
		}
		return lUserEntity;
	}

	@RequestMapping(value = "/getUserById", method = RequestMethod.POST)
	public @ResponseBody User getUserById(@RequestParam(value = "id", required = false) int userId) {
		System.out.println("User Id : " + userId);
		User lUser = null;
		try {
			lUser = lUserService.findByUserId(userId);
			if (lUser == null) {
				lUser = new User();
				lUser.setResponseCode(402);
				lUser.setResponseDescription("Data not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			lUser = new User();
			lUser.setResponseCode(401);
			lUser.setResponseDescription(e.getMessage());
		}
		return lUser;
	}

}
