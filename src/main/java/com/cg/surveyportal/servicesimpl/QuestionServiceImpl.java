package com.cg.surveyportal.servicesimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.surveyportal.entities.Question;
import com.cg.surveyportal.repositories.IQuestionRepository;
import com.cg.surveyportal.services.IQuestionService;
import com.cg.surveyportal.services.ISurveyService;

@Service
public class QuestionServiceImpl implements IQuestionService {
	
	@Autowired
	private IQuestionRepository questionRepository;
	@Autowired
	private ISurveyService surveyService;
	
	@Override
	public Question getById(long questionId) {
    Question questionById = questionRepository.findById(questionId).get();
		
		return questionById;
	}

	@Override
   public Question updateQuestion(long questionId,String questionText){
		    Question updateQuestion = questionRepository.findById(questionId).get();
			updateQuestion.setQuestionText(questionText);
			questionRepository.save(updateQuestion);
		return updateQuestion;
	}

	@Override
	public Question removeById(Long questionId) {
		
		Question deletedQuestion = questionRepository.findById(questionId).get();
		questionRepository.deleteById(questionId);
		return deletedQuestion;
	}

	@Override
	public List<Question> getQuestionDetails() {
		// TODO Auto-generated method stub
		return (List<Question>) questionRepository.findAll();
	}

//	@Override
//	public void addQuestion(String text, List<Option> option) {
//		// TODO Auto-generated method stub
//		Question question = new Question();
//		this.setOptionDefault(question);
//		question.setQuestionText(text);
//		questionRepository.save(question);
//	}
	
	public void addQuestionText(String text) {
		Question question = new Question();
		question.setQuestionText(text);
		questionRepository.save(question);
	}
	
//	@Override
//	public Question createQuestion( String questionText, List<Option> options) {
//			
//		
//		Question newQuestion = new Question();
//		//newQuestion.setSurvey(survey);
//		newQuestion.setQuestionText(questionText);
//		//newQuestion.setOptions(options);
//		
//		questionRepository.save(newQuestion);
//		
//		return newQuestion;
//	}

	@Override
	public void populate() {
		Question question = new Question();
    	question.setQuestionText("How would you rate your experience with our product?");
		this.setOptionDefault(question);
		question.setSurvey(surveyService.getSurveyById(15));
		questionRepository.save(question);
		//******************************************//
		question = new Question();
    	question.setQuestionText("How would you rate your experience you have with this organization?");
		this.setOptionDefault(question);
		question.setSurvey(surveyService.getSurveyById(16));
		questionRepository.save(question);
		//******************************************//
		question = new Question();
    	question.setQuestionText("Do you like our show? Share your experience with us.");
		this.setOptionDefault(question);
		question.setSurvey(surveyService.getSurveyById(17));
		questionRepository.save(question);
		//******************************************//
		question = new Question();
    	question.setQuestionText("How would you rate your experience of your lasr trip?");
		this.setOptionDefault(question);
		question.setSurvey(surveyService.getSurveyById(18));
		questionRepository.save(question);
	}

	@Override
	public void setOptionDefault(Question question) {
		question.setOption1("Very satisfied");
		question.setOption2("Satisfied");
		question.setOption3("Dissatisfied");
		question.setOption4("Very dissatisfied");
	}


}