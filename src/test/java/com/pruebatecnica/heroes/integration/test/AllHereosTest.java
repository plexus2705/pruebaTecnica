package com.pruebatecnica.heroes.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pruebatecnica.heroes.entity.Heroes;
import com.pruebatecnica.heroes.repository.HeroesRepository;


@SpringBootTest
public class AllHereosTest {

	String URL = "http://localhost:8080";
	String GETALL = "/heroes";
	String INSERT = "/saveHeroe";
	String DELETE = "/deleteHeroe/";
	String HEROE = "/heroe/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Mock
	private HeroesRepository heroesRepository;
	
	@BeforeEach
	public void sepUp() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllHeroesTest() {

		Heroes h1 = new Heroes();
		h1.setNombre("Spiderman");
		Heroes h2 = new Heroes();
		h2.setNombre("Batman");
		Heroes h3 = new Heroes();
		h3.setNombre("La masa");
		

		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		//poblamos la BBDD
		HttpEntity<Heroes> entity = new HttpEntity<>(h1, headers);
		ResponseEntity<?> heroe1= restTemplate.exchange(URL+INSERT, HttpMethod.POST, entity, Heroes.class); 
		assertEquals("201 CREATED", heroe1.getStatusCode().toString());
		
		HttpEntity<Heroes> entity1 = new HttpEntity<>(h2, headers);		
		ResponseEntity<?> heroe2 = restTemplate.exchange(URL+INSERT, HttpMethod.POST, entity1, Heroes.class); 
		assertEquals("201 CREATED", heroe2.getStatusCode().toString());
		
		HttpEntity<Heroes> entity2 = new HttpEntity<>(h3, headers);
		ResponseEntity<?> heroe3 =restTemplate.exchange(URL+INSERT, HttpMethod.POST, entity2, Heroes.class); 
		assertEquals("201 CREATED", heroe3.getStatusCode().toString());
		

		//recuperamos todos los Superheroes
		ResponseEntity<List> responseEntity= restTemplate.getForEntity("http://localhost:8080/heroes", List.class); 
		assertNotNull(responseEntity);
		assertEquals("200 OK",responseEntity.getStatusCode().toString());
		
		//recuperamos a spiderman
		ResponseEntity<Heroes> heroe4= restTemplate.exchange(URL+HEROE+"1", HttpMethod.GET, entity, 
				Heroes.class);
		assertNotNull(heroe4);
		assertEquals("Spiderman", heroe4.getBody().getNombre());
		
		//Eliminamos a spiderman
		ResponseEntity response= restTemplate.exchange(URL+DELETE+"1", HttpMethod.DELETE, entity, 
				ResponseEntity.class);
		assertEquals("204 NO_CONTENT",response.getStatusCode().toString());

		
	}
	
	
	

}
