package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IUsuarioDAO;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDAO.findOneByUsername(username);
	}
}