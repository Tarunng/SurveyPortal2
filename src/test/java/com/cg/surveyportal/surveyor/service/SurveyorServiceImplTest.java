package com.cg.surveyportal.surveyor.service;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.surveyportal.entities.Surveyor;
import com.cg.surveyportal.exceptions.InvalidSurveyorException;
import com.cg.surveyportal.exceptions.SurveyorNotFoundException;
import com.cg.surveyportal.repositories.ISurveyorRepository;
import com.cg.surveyportal.services.ISurveyorService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SurveyorServiceImplTest {
	
	@Autowired
	ISurveyorService surveyorService;
	
	@MockBean
	ISurveyorRepository surveyorRepository;
	
	//@Test
	//public void testgetAllSurveyors()
	//{
		
		
	//}
	
	@Test
	public void testadd() throws InvalidSurveyorException
	{
		
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		String s = "Surveyor Added Successfully";
		
		Mockito.when(surveyorRepository.save(serveyor)).thenReturn(serveyor);
		assertThat(surveyorService.add(serveyor)).isEqualTo(s);
	}
	
	@Test
	public void testgetById() throws SurveyorNotFoundException 
	{
		Long a = Integer.toUnsignedLong(12);
		Surveyor serveyor = new Surveyor();
		serveyor.setId(a);
		serveyor.setFirstName("Monica");
		serveyor.setLastName("Williams");
		serveyor.setUsername("MonsWill");
		serveyor.setSurveys(null);
		
		Mockito.when(surveyorRepository.findById(a).get()).thenReturn(serveyor);
		assertThat(surveyorService.getById(a)).isEqualTo(serveyor);
		
		
	}
	
	@Test
	public void testgetByUsername() throws SurveyorNotFoundException, InvalidSurveyorException 
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		
		Mockito.when(surveyorRepository.findByUsername("dav_aus")).thenReturn(serveyor);
		assertThat(surveyorService.getByUsername("dav_aus")).isEqualTo(serveyor);
		
		
	}
	
	

}
