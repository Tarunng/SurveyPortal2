package com.cg.surveyportal.services;

import java.util.List;

import com.cg.surveyportal.entities.Survey;
import com.cg.surveyportal.exceptions.TopicNotFoundException;

public interface ISurveyService {

	public void populateSurvey() throws TopicNotFoundException;
	public List<Survey> getAllSurveys();
	public Survey getSurveyById(long id);
	
	public void deleteAllsurveys();
}
