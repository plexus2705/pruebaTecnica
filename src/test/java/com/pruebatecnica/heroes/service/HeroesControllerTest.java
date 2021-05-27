package com.pruebatecnica.heroes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pruebatecnica.heroes.entity.Heroes;
import com.pruebatecnica.heroes.repository.HeroesRepository;
import com.pruebatecnica.heroes.service.impl.HeroesServiceImpl;


public class HeroesControllerTest {

	@InjectMocks
	private HeroesServiceImpl service;
	
	@Mock
	private HeroesRepository repository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	

	
	@Test
	public void getAllHeroesTest() {
		
		Heroes h1 = new Heroes();
		Heroes h2 = new Heroes();
		h1.setId(1L);
		h1.setNombre("Spiderman");
		h2.setId(2L);
		h2.setNombre("Batman");
		List<Heroes> list = new ArrayList<>();
		list.add(h1);
		list.add(h2);
		Mockito.when(repository.findAll()).thenReturn(list );
		List<Heroes> respuesta = service.getAllHeroes();
		assertNotNull(respuesta);
		assertTrue( respuesta.contains(h1));
		assertTrue( respuesta.contains(h2));
	}
	
	@Test
	public void getHeroeByIdTest() {
		
		
		Heroes h1 = new Heroes();
		h1.setId(1L);
		h1.setNombre("Spiderman");
		Optional<Heroes> value = Optional.of(h1);
		Mockito.when(repository.findById(1L)).thenReturn(value );
		Optional<Heroes> resp = service.getHeroe(1L);
		Heroes respuesta = resp.get();
		assertNotNull(respuesta);
		assertEquals("Spiderman", respuesta.getNombre());
	}
	
	@Test
	public void deleteHeroeTest() {
		

		Mockito.doNothing().when(repository).deleteById(1L);
		 service.deleteHeroe(1L);;
		Mockito.verify(repository).deleteById(1L);
	}
	
	@Test
	public void getHeroesByCoincidenceTest() {
		String param = "man";
		Heroes h1 = new Heroes();
		Heroes h2 = new Heroes();
		Heroes h3 = new Heroes();
		h1.setId(1L);
		h1.setNombre("Spiderman");
		h2.setId(2L);
		h2.setNombre("Batman");
		h3.setId(2L);
		h3.setNombre("La masa");
		List<Heroes> list = new ArrayList<>();
		list.add(h1);
		list.add(h2);
		list.add(h3);
		Mockito.when(repository.findAll()).thenReturn(list );
		List<Heroes> respuesta = service.getHeroesByCoincidence(param);
		assertNotNull(respuesta);
		assertEquals("Spiderman", respuesta.get(0).getNombre());
		assertEquals("Batman", respuesta.get(1).getNombre());
		assertEquals(2, respuesta.size());
	}
	
	@Test
	public void saveHeroeTest() {
		Heroes h1 = new Heroes();
		h1.setId(1l);
		h1.setNombre("Spiderman");
		
		Heroes object = new Heroes();
		object.setNombre("Spiderman");
		

		Mockito.when(repository.save(object)).thenReturn(h1);
		Heroes respuesta = service.saveHeroe(object);
		assertNotNull(respuesta);
		assertEquals("Spiderman", respuesta.getNombre());
	}
}
