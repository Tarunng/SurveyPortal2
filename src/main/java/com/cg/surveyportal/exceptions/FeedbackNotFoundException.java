package com.cg.surveyportal.exceptions;

import java.util.concurrent.Executors;

public class FeedbackNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeedbackNotFoundException(){

    }

    public FeedbackNotFoundException(String msg){
        super(msg);
    }
}
