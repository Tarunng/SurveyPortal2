package com.cg.surveyportal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.surveyportal.entities.Participant;
import com.cg.surveyportal.entities.Survey;
import com.cg.surveyportal.entities.Surveyor;
import com.cg.surveyportal.exceptions.InvalidSurveyorException;
import com.cg.surveyportal.exceptions.ParticipantNotFoundException;
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
	
	
	Surveyor sv;
	Survey s;
	
	
	@BeforeEach
	public void init() {
		sv = new Surveyor();
		sv.setId(20l);
		sv.setFirstName("abc");
		sv.setLastName("xyz");
		sv.setUsername("cvb");
		sv.setSurveys(null);
	}
	
	
	@Test
	@DisplayName ("Positive test case for getting all Surveyor")
	public void testgetAllSurveyors()
	{

		Mockito.when(surveyorRepository.findAll()).thenReturn(Stream.of(sv).collect(Collectors.toList()));
		assertEquals(1, surveyorService.getAllSurveyors().size());
	}
	
	@Test
	@DisplayName ("Negative test case for getting all Surveyor")
	public void testgetAllSurveyorsNegative()
	{
		
		Mockito.when(surveyorRepository.findAll()).thenReturn(Stream.of(sv).collect(Collectors.toList()));
		assertNotEquals(2, surveyorService.getAllSurveyors().size());
	}
	
	@Test
	@DisplayName ("Positive test case for Adding Surveyor")
	
	public void testadd() throws InvalidSurveyorException
	{
		
		
		String s = "Surveyor Added Successfully";
		
		Mockito.when(surveyorRepository.save(sv)).thenReturn(sv);
		assertThat(surveyorService.add(sv)).isEqualTo(s);
	}
	
	@Test
	@DisplayName ("Negative test case for Adding Surveyor")
	
	public void testaddNegative() throws InvalidSurveyorException
	{
		
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		
		String s = "Surveyor  Added Successfully";
		
		Mockito.when(surveyorRepository.save(sv)).thenReturn(sv);
		assertThat(surveyorService.add(serveyor)).isNotEqualTo(s);
	}
	
	
	
	@Test
	@DisplayName ("Positive Test case for getting Surveyor by ID")
	public void testgetById() throws SurveyorNotFoundException 
	{
		Mockito.when(surveyorRepository.findById(sv.getId())).thenReturn(Optional.of(sv));
		assertEquals(sv,surveyorService.getById(21l));
	}
	
	
	@Test
	@DisplayName ("Negative Test case for getting Surveyor by ID")
	public void testgetByIdNegative() throws SurveyorNotFoundException 
	{
		Mockito.when(surveyorRepository.findById(sv.getId())).thenReturn(Optional.of(sv));
		Assertions.assertThrows(SurveyorNotFoundException.class, ()-> surveyorService.getById(21l));
	}
	
	
	
	@Test
	@DisplayName ("Positive Test case for getting Surveyor by Username")
	public void testgetByUsername() throws SurveyorNotFoundException, InvalidSurveyorException 
	{
		
		
		Mockito.when(surveyorRepository.findByUsername("dav_aus")).thenReturn(sv);
		assertThat(surveyorService.getByUsername("dav_aus")).isEqualTo(sv);
		
		
	}
	
	@Test
	@DisplayName ("Negative Test case for getting Surveyor by Username")
	public void testgetByUsernameNegative() throws SurveyorNotFoundException, InvalidSurveyorException 
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus_hello");
		serveyor.setSurveys(null);
		
		
		Mockito.when(surveyorRepository.findByUsername("dav_aus")).thenReturn(sv);
		assertThat(surveyorService.getByUsername("dav_aus")).isNotEqualTo(serveyor);
		
		
	}
	
	@Test
	@DisplayName("Positive Test Case for updating record")
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
	@DisplayName("Negative Test Case for updating record")
	public void testupdateNegative() throws InvalidSurveyorException, SurveyorNotFoundException
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(21l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		String s = "Surveyor Not Updated Successfully";
		
		Mockito.when(surveyorRepository.save(serveyor)).thenReturn(serveyor);
		assertThat(surveyorService.update(serveyor)).isNotEqualTo(s);
		
	}
	
	
	
	@Test
	@DisplayName("Positive Test Case for Getting Record Count")
	public void testgetRecordsCount()
	{
		
		Long a = 2l;
		Mockito.when(surveyorRepository.count()).thenReturn(a);
		assertThat(surveyorService.getRecordsCount()).isEqualTo(a);
	}
	
	@Test
	@DisplayName("Negative Test Case for Getting Record Count")
	public void testgetRecordsCountNegative()
	{
		
		Long a = 2l;
		Long b = 3l;
		Mockito.when(surveyorRepository.count()).thenReturn(a);
		assertThat(surveyorService.getRecordsCount()).isNotEqualTo(b);
	}
	
	
	
	
	@Test
	@DisplayName("Positive Test Case for removing all Records Count")
	
	public void testremoveAllRecords()
	{
		String s = "All Records Deleted ";
		assertThat(surveyorService.removeAllRecords()).isEqualTo(s);
		Mockito.verify(surveyorRepository).deleteAll();
	}
	
	
	@Test
	@DisplayName("Negative Test Case for removing all Records Count")
	
	public void testremoveAllRecordsNegative()
	{
		String s = "All Records Not Deleted ";
		assertThat(surveyorService.removeAllRecords()).isNotEqualTo(s);
		Mockito.verify(surveyorRepository).deleteAll();
	}
	
	
	
	@Test
	@DisplayName("Positive Test Case for remove record by id")
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
	
	@Test
	@DisplayName("Negative Test Case for remove record by id")
	public void testremoveByIdNegative() throws InvalidSurveyorException
	{
		Surveyor serveyor = new Surveyor();
		serveyor.setId(23l);
		serveyor.setFirstName("David");
		serveyor.setLastName("Austin");
		serveyor.setUsername("dav_aus");
		serveyor.setSurveys(null);
		
		String s = "Data Not Deleted Successfully";
		
		assertThat(surveyorService.removeById(23l)).isNotEqualTo(s);
		Mockito.verify(surveyorRepository).deleteById(23l);
	}
	
	
	
}
