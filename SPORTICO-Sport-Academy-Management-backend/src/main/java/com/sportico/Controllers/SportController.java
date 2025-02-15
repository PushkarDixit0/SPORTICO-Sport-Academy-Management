	package com.sportico.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportico.DTO.SportDTO;
import com.sportico.Service.SportsService;



@RestController
@RequestMapping("/sports")
public class SportController {
	@Autowired
	public SportsService sportsService;
	
	@GetMapping("/")
	public ResponseEntity<?> getSports() {
		return ResponseEntity.ok(sportsService.getAllSports()) ;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> postMethodName(@RequestBody SportDTO sport) {
		
		return ResponseEntity.ok(sportsService.addSport(sport));
	}
	
	
}
