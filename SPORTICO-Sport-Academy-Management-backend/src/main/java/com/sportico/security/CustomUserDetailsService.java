package com.sportico.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportico.DAO.UsersDao;
import com.sportico.pojos.User;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	// depcy
	private UsersDao usersDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Email !!!"));
		return new CustomUserDetails(user);
	}

}
