package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.SportDTO;

public interface SportsService {

	List<SportDTO> getAllSports();

	SportDTO addSport(SportDTO sp);

}
