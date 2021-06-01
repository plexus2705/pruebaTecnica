package com.pruebatecnica.heroes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.heroes.anotaciones.Tiempo;
import com.pruebatecnica.heroes.entity.Heroes;
import com.pruebatecnica.heroes.repository.HeroesRepository;
import com.pruebatecnica.heroes.service.HeroesService;


/**
 * The Class HeroesServiceImpl.
 */
@Service
public class HeroesServiceImpl implements HeroesService{

	/** The heroes repository. */
	@Autowired
	private HeroesRepository heroesRepository;
	
	/**
	 * Gets the all heroes.
	 *
	 * @return the all heroes
	 */
	@Override
	public List<Heroes> getAllHeroes() {
		List<Heroes> heroes = new ArrayList<>(); 
		heroesRepository.findAll().forEach(heroe -> heroes.add(heroe)); 
		return heroes;
	}

	/**
	 * Gets the heroe.
	 *
	 * @param id the id
	 * @return the heroe
	 */
	@Override
	public Optional<Heroes> getHeroe(long id) {
		
		long tiempoInicio;
		
		Optional<Heroes> respuesta = heroesRepository.findById(id); 
		return  respuesta;
	}

	/**
	 * Delete heroe.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteHeroe(long id) {
		
		heroesRepository.deleteById(id);
		
	}


	/**
	 * Gets the heroes by coincidence.
	 *
	 * @param param the param
	 * @return the heroes by coincidence
	 */
	@Override
	public List<Heroes> getHeroesByCoincidence(String param) {
		List<Heroes> heroes = new ArrayList<>(); 
		List<Heroes> respuesta = new ArrayList<>(); 
		heroesRepository.findAll().forEach(heroe -> heroes.add(heroe)); 
		
		for(Heroes heroe: heroes) {
			if(heroe.getNombre().contains(param)) {
				respuesta.add(heroe);
			}
		}
		
		return respuesta;
	}

	/**
	 * Save heroe.
	 *
	 * @param heroe the heroe
	 * @return the heroes
	 */
	@Override
	public Heroes saveHeroe(Heroes heroe) {
		return heroesRepository.save(heroe);
	}

	/**
	 * Update heroe.
	 *
	 * @param heroe the heroe
	 * @return the heroes
	 */
	@Override
	public Heroes updateHeroe(Heroes heroe) {
		return heroesRepository.save(heroe);
	}

}
