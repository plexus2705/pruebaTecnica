package com.pruebatecnica.heroes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.pruebatecnica.heroes.entity.Heroes;


/**
 * The Interface HeroesService.
 */
public interface HeroesService {

	/**
	 * Gets the all heroes.
	 *
	 * @return the all heroes
	 */
	public List<Heroes> getAllHeroes();
	
	/**
	 * Gets the heroe.
	 *
	 * @param id the id
	 * @return the heroe
	 */
	public Optional<Heroes> getHeroe(long id) ;
	
	/**
	 * Delete heroe.
	 *
	 * @param id the id
	 */
	public void deleteHeroe( long id) ; 
	
	/**
	 * Save heroe.
	 *
	 * @param heroe the heroe
	 * @return the heroes
	 */
	public Heroes saveHeroe( Heroes heroe); 
	
	/**
	 * Update heroe.
	 *
	 * @param heroe the heroe
	 * @return the heroes
	 */
	public Heroes updateHeroe( Heroes heroe); 
	
	/**
	 * Gets the heroes by coincidence.
	 *
	 * @param param the param
	 * @return the heroes by coincidence
	 */
	public List<Heroes> getHeroesByCoincidence(String param);
}
