package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.GetUserdto;
import com.sportico.DTO.LoginResponseDTO;
import com.sportico.DTO.LoginUserDTO;
import com.sportico.DTO.PostUserdto;
import com.sportico.pojos.User;



public interface UsersService {

	List<GetUserdto> getallUSERS();
	
	String savecoach(PostUserdto entity);

	String Updateuser(Long userid,PostUserdto entity);

	String UpdateuserEmail(Long userid, String email);

	String UpdateuserPass(String email, String oLDPasswd, String nEWPasswd);

	String deleteuser(Long userid);

	User GetuserbyId(Long userid);

	List<GetUserdto> getallCoach();

	List<GetUserdto> getallusers();

	LoginResponseDTO saveUser(PostUserdto postUserdto);

	LoginResponseDTO loginUser(LoginUserDTO userdto);

	

	

	



}
