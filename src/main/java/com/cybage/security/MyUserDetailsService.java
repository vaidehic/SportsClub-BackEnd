package com.cybage.security;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybage.entity.Users;
import com.cybage.repository.UsersRepository;

import java.util.*;

import javax.servlet.http.HttpSession;

@Service(value = "userService")
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository ur;
	private boolean accountNonLocked;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = ur.findByEmail(email);		
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				true,true,true,user.isAccountNonLocked(),getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(Users user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

		return authorities;
	}	   
}