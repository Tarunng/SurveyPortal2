package com.cg.surveyportal.services;

import java.util.List;

import com.cg.surveyportal.entities.Question;


public interface IQuestionService {

    Question getById(long id) ;
	List<Question> getQuestionDetails();
    Question removeById(Long questionId);
	Question updateQuestion(long questionId,String questionText);
	//void addQuestion (String text, List<Option> option);
	void addQuestionText (String text);
	//Question createQuestion(String questionText, List<Option> options);
	public void populate();
	public void setOptionDefault(Question question);
}