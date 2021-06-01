package com.pruebatecnica.heroes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.heroes.anotaciones.Tiempo;
import com.pruebatecnica.heroes.entity.Heroes;
import com.pruebatecnica.heroes.exceptions.HeroeNotFoundException;
import com.pruebatecnica.heroes.service.HeroesService;


/**
 * The Class HeroesController.
 */
@RestController
public class HeroesController {

	/** The heroes service. */
	@Autowired
	private HeroesService heroesService;

	/**
	 * Gets the all heroes.
	 *
	 * @return the all heroes
	 */
	@GetMapping("/heroes")
	@Tiempo
	public ResponseEntity<List<Heroes>> getAllHeroes() {
		List<Heroes> respuesta = heroesService.getAllHeroes();
		if(respuesta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(respuesta) ;
		}
		
		
	}

	/**
	 * Gets the heroe.
	 *
	 * @param id the id
	 * @return the heroe
	 */
	@GetMapping("/heroe/{id}")
	@Tiempo
	public Heroes getHeroe(@PathVariable("id") long id) {
		
		return heroesService.getHeroe(id)
				.orElseThrow(() -> new HeroeNotFoundException(id));
	}

	/**
	 * Delete heroe.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/deleteHeroe/{id}")
	@Tiempo
	public ResponseEntity<?> deleteHeroe(@PathVariable("id") long id) {
		
		heroesService.getHeroe(id)
			.orElseThrow(() -> new HeroeNotFoundException(id));
		
		heroesService.deleteHeroe(id);
		
		return ResponseEntity.noContent().build();
	}

	/**
	 * Save hero.
	 *
	 * @param heroe the heroe
	 * @return the response entity
	 */
	@PostMapping("/saveHeroe")
	@Tiempo
	public ResponseEntity<Heroes> saveHero(@RequestBody Heroes heroe) {
		Heroes respuesta = heroesService.saveHeroe(heroe);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
	
	/**
	 * Update hero.
	 *
	 * @param heroe the heroe
	 * @return the heroes
	 */
	@PutMapping("/updateHero")
	@Tiempo
	public Heroes updateHero(@RequestBody Heroes heroe) {
		return heroesService.getHeroe(heroe.getId()).map(h -> {
			h.setNombre(heroe.getNombre());
			return heroesService.updateHeroe(heroe);
		}).orElseThrow(() -> new HeroeNotFoundException(heroe.getId()));

	}
	
	/**
	 * Gets the heroes by coincidence.
	 *
	 * @param param the param
	 * @return the heroes by coincidence
	 */
	@GetMapping("/heroesByCoincidence")
	@Tiempo
	public  ResponseEntity<?> getHeroesByCoincidence(@PathVariable("param") String param) {
		List<Heroes> respuesta = heroesService.getHeroesByCoincidence(param);
		if(respuesta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(respuesta) ;
		}

	}
	
}
