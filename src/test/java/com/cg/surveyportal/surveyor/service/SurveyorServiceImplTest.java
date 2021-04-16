package com.cg.surveyportal.surveyor.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

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
import com.google.common.base.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest

public class SurveyorServiceImplTest {
	
	@Autowired
	ISurveyorService surveyorService;
	
	@MockBean
	ISurveyorRepository surveyorRepository;
	
	@Test
	public void testgetAllSurveyors()
	{
		
		List<Surveyor> list = new ArrayList<>();
		
		Surveyor serveyor = new Surveyor();
		serveyor.setId(24l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		
		list.add(serveyor);
		
		serveyor = new Surveyor();
		serveyor.setId(22l);
		serveyor.setFirstName("Rahul");
		serveyor.setLastName("jain");
		serveyor.setUsername("rah_jai");
		serveyor.setSurveys(null);
		
		list.add(serveyor);
		
		Mockito.when(surveyorRepository.findAll()).thenReturn(list);
		assertThat(surveyorService.getAllSurveyors()).isEqualTo(list);
		
		
	}
	
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
		
		Surveyor sv = new Surveyor();
		sv.setId(1l);
		sv.setFirstName("David");
		sv.setLastName("Austin");
		sv.setUsername("dav_aus");
		sv.setSurveys(null);
		Surveyor sv1 = surveyorRepository.findById(sv.getId()).get();
		Mockito.when(surveyorRepository.findById(sv1.getId()).orElseThrow()).thenReturn(sv1);
		assertThat(surveyorService.getById(sv1.getId())).isEqualTo(sv1);
		
		
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
	
	@Test
	public void testupdate() throws InvalidSurveyorException, SurveyorNotFoundException
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		String s = "Surveyor Updated Successfully";
		
		Mockito.when(surveyorRepository.save(serveyor)).thenReturn(serveyor);
		assertThat(surveyorService.update(serveyor)).isEqualTo(s);
		
	}
	
	@Test
	public void testgetRecordsCount()
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		
		serveyor = new Surveyor();
		serveyor.setId(22l);
		serveyor.setFirstName("Rahul");
		serveyor.setLastName("jain");
		serveyor.setUsername("rah_jai");
		serveyor.setSurveys(null);
		
		
		Long a = 2l;
		
		
		Mockito.when(surveyorRepository.count()).thenReturn(a);
		assertThat(surveyorService.getRecordsCount()).isEqualTo(a);
		
		
	}
	
	@Test
	public void testremoveAllRecords()
	{
		String s = "All Records Deleted ";
		assertThat(surveyorService.removeAllRecords()).isEqualTo(s);
		Mockito.verify(surveyorRepository).deleteAll();
	}
	
	@Test
	public void testremoveById() throws InvalidSurveyorException
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(23l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		
		String s = "Data Deleted Successfully";
		
		assertThat(surveyorService.removeById(23l)).isEqualTo(s);
		Mockito.verify(surveyorRepository).deleteById(23l);
	}
	
	
}
