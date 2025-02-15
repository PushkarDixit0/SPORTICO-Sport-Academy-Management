package com.sportico.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportico.pojos.Roles;
import com.sportico.pojos.User;

public interface UsersDao extends JpaRepository<User, Long> {

	List<User> findByRole(Roles role);
	
	User findByEmailAndPassword (String email,String pass);
	
//	User findByEmail(String email);
	
	Optional<User>  findByEmail(String email);
	
	boolean existsByEmail(String email);

}
