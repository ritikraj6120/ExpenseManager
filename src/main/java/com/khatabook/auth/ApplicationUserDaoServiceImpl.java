package com.khatabook.auth;

import com.khatabook.entities.User;
import com.khatabook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("mysql")
public class ApplicationUserDaoServiceImpl implements ApplicationUserDao {
    @Autowired 
	private UserRepository userRepository;
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    	Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isPresent()) {
        	User user = optionalUser.get();
        	ApplicationUser applicationUser = new ApplicationUser(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>(),
                    true,
                    true,
                    true,
                    true
            );
            Optional<ApplicationUser> optionalApplicationUser = Optional.of(applicationUser);
            return optionalApplicationUser;
        }
        else 
        	{
        		return Optional.empty();
        	}
    }

}
