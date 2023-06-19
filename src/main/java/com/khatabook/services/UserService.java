package com.khatabook.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khatabook.entities.User;
import com.khatabook.repository.UserRepository;
import com.khatabook.request.user.UserLoginDTO;
import com.khatabook.request.user.UserRegistrationDTO;
import com.khatabook.response.user.LoggedInUser;
import com.khatabook.response.user.SignedUpUser;
import com.khatabook.response.user.UserLoginResponseDTO;
import com.khatabook.response.user.UserSignUpResponseDTO;



@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 public User findByEmail(String email) {
		 Optional<User> optionalUser = userRepository.findByEmail(email);
	        if(optionalUser.isPresent()) {
				return optionalUser.get();
	        }
	        else return null;
	 }
	 
	 public boolean verifyPassword(String rawPassword, String encodedPassword) {
	        return passwordEncoder.matches(rawPassword, encodedPassword);
	  }
	 
	public UserSignUpResponseDTO signupUser(UserRegistrationDTO userRegistrationDTO)
	{
		User user = new User();
		if(findByEmail(userRegistrationDTO.getUsername())!=null)
		{
			return new UserSignUpResponseDTO(409, "Error", "User Already Existed with given email-address", null);
		}
		user.setUsername(userRegistrationDTO.getUsername());
		user.setEmail(userRegistrationDTO.getEmail());
		String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
		user.setPassword(encodedPassword);
		User Createduser = userRepository.save(user);
		SignedUpUser signedUpUser = new SignedUpUser(Createduser.getUsername(), Createduser.getEmail());
		return new UserSignUpResponseDTO(200, "Success", "User Created", signedUpUser);
	}
	
	public ResponseEntity<UserLoginResponseDTO> loginUser(UserLoginDTO userLoginDTO) 
	{
		User user = findByEmail(userLoginDTO.getEmail());
        
        if (user != null && verifyPassword(userLoginDTO.getPassword(), user.getPassword())) {
        	LoggedInUser loggedInUser = new LoggedInUser(user.getUsername(),user.getEmail());
        	UserLoginResponseDTO userLoginResponseDTO= new UserLoginResponseDTO(200, "Success", "Login Successful", loggedInUser);
        	return ResponseEntity.status(userLoginResponseDTO.getStatus_code()).body(userLoginResponseDTO);
        } else {
        	UserLoginResponseDTO userLoginResponseDTO= new UserLoginResponseDTO(200, "Error", "Invalid credentials", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userLoginResponseDTO);
        }
		
	}
	
	
}
