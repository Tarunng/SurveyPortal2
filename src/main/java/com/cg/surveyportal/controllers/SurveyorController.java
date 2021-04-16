package com.cg.surveyportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.surveyportal.entities.Surveyor;
import com.cg.surveyportal.exceptions.InvalidSurveyorException;
import com.cg.surveyportal.exceptions.SurveyorNotFoundException;
import com.cg.surveyportal.services.ISurveyorService;

@RestController
@RequestMapping("/surveyor")
public class SurveyorController {

	@Autowired
	ISurveyorService surveyorService;
	
	@GetMapping("/populate")
	private void populate()
	{
		surveyorService.populateSurveyor();
	}
	
	@GetMapping("/count")
	private ResponseEntity<Long> countRecords()
	{
		return new ResponseEntity<>(surveyorService.getRecordsCount(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allsurveyors")
	private ResponseEntity<List<Surveyor>> getAllSurveyors()
	{
		return new ResponseEntity<>( surveyorService.getAllSurveyors(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/findbyid/{id}")
	private ResponseEntity<Surveyor> getSurveyorById(@PathVariable("id") String id) throws NumberFormatException, SurveyorNotFoundException
	{
		return new ResponseEntity<>(surveyorService.getById(Long.parseLong(id)),HttpStatus.OK);
	}
	
	@GetMapping("/findbyusername/{username}")
	private ResponseEntity<Surveyor> getSurveyorByUsername(@PathVariable("username") String username) throws InvalidSurveyorException
	{
		return new ResponseEntity<>(surveyorService.getByUsername(username),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<String> deleteById(@PathVariable("id") long id) throws InvalidSurveyorException
	{
		return new ResponseEntity<>(surveyorService.removeById(id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	private ResponseEntity<String> addSurveyor(@PathVariable Surveyor surveyor) throws InvalidSurveyorException
	{
		return new ResponseEntity<>(surveyorService.add(surveyor),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("deleteAll")
	private ResponseEntity<String> deleteAllRecords()
	{
		return new ResponseEntity<>(surveyorService.removeAllRecords(),HttpStatus.OK);
	}
	
	@PutMapping("update")
	private ResponseEntity<String> updateRecord(@PathVariable Surveyor surveyor) throws InvalidSurveyorException, SurveyorNotFoundException
	{
		return new ResponseEntity<>(surveyorService.update(surveyor),HttpStatus.OK);
		
	}
 }
