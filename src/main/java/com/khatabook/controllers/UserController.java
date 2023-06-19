package com.khatabook.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.request.user.UserRegistrationDTO;
import com.khatabook.response.user.UserSignUpResponseDTO;
import com.khatabook.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
		@PostMapping("/signup")
		public ResponseEntity<UserSignUpResponseDTO> SignupUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
		try {
				UserSignUpResponseDTO customUserSignUpResponse=userService.signupUser(userRegistrationDTO);
				return ResponseEntity.status(customUserSignUpResponse.getStatus_code()).body(customUserSignUpResponse);
			} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			UserSignUpResponseDTO customUserSignUpResponse =new UserSignUpResponseDTO(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customUserSignUpResponse);
			}
		}
		
//		@PostMapping("/login")
//		public ResponseEntity<UserLoginResponseDTO> LoginUser(@Valid @RequestBody UserLoginDTO userLoginDTO){
//		try 
//		{
//			return userService.loginUser(userLoginDTO);
//		}
//		catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//			UserLoginResponseDTO userLoginResponseDTO =new UserLoginResponseDTO(500, "Error", "Internal Server Error", null);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userLoginResponseDTO);
//		}
	}
	
	