package com.sportico.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportico.DAO.SportsDao;
import com.sportico.DTO.SportDTO;
import com.sportico.custom_exceptions.SportAlreadyExistsException;
import com.sportico.pojos.Sport;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SportsServiceImpl implements SportsService{

	@Autowired
	public SportsDao sportsDao;
	
	@Autowired
	public ModelMapper modelMapper;
	
	
	@Override
	public List<SportDTO> getAllSports(){
		 List<Sport> lst=  sportsDao.findAll();
		 
		 return lst.stream().map(s->modelMapper.map(s, SportDTO.class)).collect(Collectors.toList());
	}
	
	@Override
	public SportDTO addSport(SportDTO sp) {
		if(sportsDao.findByName(sp.getName()).isPresent()) {
			throw new SportAlreadyExistsException("sport Already exists");
		}
		
		Sport sport =   modelMapper.map(sp, Sport.class);
	    return modelMapper.map(sportsDao.save(sport), SportDTO.class); 
	
	}
}
