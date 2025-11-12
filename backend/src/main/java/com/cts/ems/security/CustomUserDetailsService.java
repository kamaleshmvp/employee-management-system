package com.cts.ems.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.ems.entity.User;
import com.cts.ems.exception.UserNotFoundException;
import com.cts.ems.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	  @Autowired
      private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException("Username you have given is not registered"));
		return new CustomUserDetails(user);
	}
      
}
