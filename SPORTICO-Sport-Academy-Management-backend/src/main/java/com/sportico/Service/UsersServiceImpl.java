package com.sportico.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sportico.DAO.UsersDao;
import com.sportico.DTO.GetUserdto;
import com.sportico.DTO.LoginResponseDTO;
import com.sportico.DTO.LoginUserDTO;
import com.sportico.DTO.PostUserdto;
import com.sportico.pojos.Roles;
import com.sportico.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	public UsersDao userDao;

	@Autowired
	public ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<GetUserdto> getallUSERS() {
		List<User> alluser = userDao.findAll();
		return alluser.stream().map(user -> mapper.map(user, GetUserdto.class)).collect(Collectors.toList());

	}

	
	@Override
	public User GetuserbyId(Long userid) {
		
		return userDao.findById(userid).orElse(null);
	}

	
	@Override
	public LoginResponseDTO saveUser(PostUserdto postUserdto) {
	    if (postUserdto == null) {
	        throw new RuntimeException("Invalid user data. Cannot save.");
	    }

	    if(userDao.existsByEmail(postUserdto.getEmail()))
			throw new RuntimeException("Email already exists !!!");
		

	    try {
	    	
	        User user = mapper.map(postUserdto, User.class);
	        user.setRole(Roles.ROLE_USER);
	        user.setPassword(encoder.encode(user.getPassword()));
	        
	        userDao.save(user);
	        
	        return new LoginResponseDTO(user.getId(), user.getFname(), user.getEmail(), user.getRole());
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to save user: " + e.getMessage());
	    }
	}

	@Override
	public LoginResponseDTO loginUser(LoginUserDTO userdto) {
	    // Fetch user by email
	    User user = userDao.findByEmail(userdto.getEmail()).orElseThrow(() -> 
	        new RuntimeException("User not found!"));  // Throw error if user doesn't exist

	    // Use PasswordEncoder to compare the provided password with the hashed password in the database
	    if (!encoder.matches(userdto.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid credentials!");  // Incorrect password
	    }

	    // Return user details in the response DTO
	    return new LoginResponseDTO(user.getId(), user.getFname(), user.getEmail(), user.getRole());
	}

	
	
	@Override
	public String savecoach(PostUserdto entity) {
		if (entity != null) {
			User user = mapper.map(entity, User.class);
			user.setRole(Roles.ROLE_COACH);
	        user.setPassword(encoder.encode(user.getPassword()));
			userDao.save(user);
			return "Successfully save";
		} else {
			return "input data is invalid";
		}
	}

	

	@Override
	public String Updateuser(Long userid, PostUserdto entity) {
	    if (entity != null) {
	        User user = userDao.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));

	        if (entity.getFname() != null) user.setFname(entity.getFname());
	        if (entity.getLname() != null) user.setLname(entity.getLname());
	        if (entity.getEmail() != null) user.setEmail(entity.getEmail());
	        if (entity.getMobNo() != null) user.setMobNo(entity.getMobNo());
	        
	        userDao.save(user);
	        return "Successfully Updated";
	    } else {
	        return "Invalid request";
	    }
	}


	@Override
	public String UpdateuserEmail(Long userid, String email) {
		if (email != null) {
			User user = userDao.findById(userid).orElseThrow();
			
			user.setEmail(email);
			userDao.save(user);
			return "Successfully Email Updated";
			
		} else {

			return null;
		}
		
	}

	@Override
	public String UpdateuserPass(String email, String OLDpasswd, String NEWpasswd) {
		if (email != null) {
			User user = userDao.findByEmailAndPassword(email,OLDpasswd);
			
			user.setPassword(NEWpasswd);
			userDao.save(user);
			return "Successfully Password Updated";
			
		} else {

			return null;
		}
		
	}

	@Override
	public String deleteuser(Long userid) {
		userDao.deleteById(userid);
		return "Successfully user delete";
		
	}


	
	
	
	@Override
	public List<GetUserdto> getallCoach() {
		List<User> alluser = userDao.findByRole(Roles.ROLE_COACH);
		return alluser.stream().map(user -> mapper.map(user, GetUserdto.class)).collect(Collectors.toList());

	}


	@Override
	public List<GetUserdto> getallusers() {
		List<User> alluser = userDao.findByRole(Roles.ROLE_USER);
		return alluser.stream().map(user -> mapper.map(user, GetUserdto.class)).collect(Collectors.toList());
	}



	
	
	
	
	
	
	


	

}
