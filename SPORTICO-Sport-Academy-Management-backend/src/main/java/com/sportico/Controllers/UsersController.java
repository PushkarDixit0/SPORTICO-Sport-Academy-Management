package com.sportico.Controllers;



import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportico.DTO.APIResponse;
import com.sportico.DTO.LoginResponseDTO;
import com.sportico.DTO.LoginUserDTO;
import com.sportico.DTO.PostUserdto;
import com.sportico.DTO.SigninResponse;
//import com.sportico.Service.CaptchaService;
import com.sportico.Service.UsersService;
import com.sportico.security.JwtUtils;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	public UsersService usersService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;


//	@Autowired
//	public CaptchaService captchaService;
	
	public UsersController() {
		System.out.println("UsersService Running");
	}
	
	@GetMapping("/debug")
	public ResponseEntity<?> debugUser() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    return ResponseEntity.ok(auth);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<?> getMethodGetAll() {
		return ResponseEntity.ok(usersService.getallUSERS());
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> GetMethodUser(@PathVariable Long userid) {
		return ResponseEntity.ok(usersService.GetuserbyId(userid));
	}

	@PostMapping("/adduser")
	public ResponseEntity<?> postMethodInsertUSER(@RequestBody @Valid PostUserdto entity) {
	    LoginResponseDTO userDetails = usersService.saveUser(entity);

	    List<GrantedAuthority> authorities = Collections.singletonList(
	            new SimpleGrantedAuthority("ROLE_" + userDetails.getRole())
	    );

	    // Generate JWT Token
	    String jwtToken = jwtUtils.generateJwtToken(
	            new UsernamePasswordAuthenticationToken(userDetails.getEmail(), null, authorities)
	    );

	    // Prepare response
	    SigninResponse signinResponse = new SigninResponse(jwtToken, "User registered successfully!", userDetails);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(signinResponse);
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginUserDTO request) {
	    System.out.println("In sign-in: " + request);

	    try {
	        LoginResponseDTO userDetails = usersService.loginUser(request);

	        List<GrantedAuthority> authorities = Collections.singletonList(
	                new SimpleGrantedAuthority("ROLE_" + userDetails.getRole())
	        );

	        // Generate JWT Token
	        String jwtToken = jwtUtils.generateJwtToken(
	                new UsernamePasswordAuthenticationToken(userDetails.getEmail(), null, authorities)
	        );

	        // Prepare response
	        SigninResponse signinResponse = new SigninResponse(jwtToken, "Successful Auth!!!!", userDetails);

	        return ResponseEntity.status(HttpStatus.OK).body(signinResponse);
	    
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", "Invalid credentials!"));
	    }
	}


	
//	 @PostMapping("/login")
//	    public ResponseEntity<?> login(@RequestBody LoginUserDTO loginRequestDTO) {
//
//	        LoginResponseDTO response = usersService.loginUser(loginRequestDTO);
//	        return ResponseEntity.ok(response);
//	    }
	
	@PostMapping("/coach/save")
	public ResponseEntity<?> postMethodInsertCOACH(@RequestBody PostUserdto entity) {
		if(entity!=null) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(usersService.savecoach(entity)));
	}
		return null;		
	}
	
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<?> putMethodUpdate(@PathVariable Long userid, @RequestBody PostUserdto entity) {
		if(entity!=null) {
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(usersService.Updateuser(userid, entity)));
	}
		return null;
		
	}
	
	@PutMapping("/update/email/{userid}")
	public ResponseEntity<?> putMethodEmailUpdate(@PathVariable Long userid, @RequestBody String Email) {
		if(Email!=null) {
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(usersService.UpdateuserEmail(userid, Email)));
	}
		return null;
		
	}
	
	@PutMapping("/update/password")
	public ResponseEntity<?> putMethodPassUpdate(@RequestBody String Email,@RequestBody  String OLDPasswd, @RequestBody  String NEWPasswd) {
		if(Email!=null) {
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(usersService.UpdateuserPass(Email,OLDPasswd,NEWPasswd)));
	}
		return null;
		
	}
	
	@DeleteMapping("/user/{userid}")
	public ResponseEntity<?> DeleteMethodUser(@PathVariable Long userid) {
		return ResponseEntity.ok(new APIResponse(usersService.deleteuser(userid)));
	}
	
	

	@GetMapping("/allrolecoaches")
	public ResponseEntity<?> getMethodAllCoach() {
		return ResponseEntity.ok(usersService.getallCoach());
	}

	
	@GetMapping("/allroleusers")
	public ResponseEntity<?> getMethodAllusers() {
		return ResponseEntity.ok(usersService.getallusers());
	}

	
	
	
	
	
	
	
	
	
	
}
