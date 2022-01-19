package com.neosoft.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.myexception.ResourceNotFoundException;

@Service
public class MyUserDetailsService implements UserDetailsService{

	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(() -> new ResourceNotFoundException("User", "UserName",userName));
		return user.map(MyUserDetails::new).get();
	}
	
	
	
	
	
	
	
	
	
}
