package com.cg.surveyportal.services;
import java.util.List;

import com.cg.surveyportal.entities.Feedback;
import com.cg.surveyportal.exceptions.ParticipantNotFoundException;


public interface IFeedbackService {
	
	public void populateFeedback() throws ParticipantNotFoundException;
	public List<Feedback> getAll();
}