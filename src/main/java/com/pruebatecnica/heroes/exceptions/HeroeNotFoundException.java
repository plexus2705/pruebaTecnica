package com.pruebatecnica.heroes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class HeroeNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroeNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new heroe not found exception.
	 *
	 * @param id the id
	 */
	public HeroeNotFoundException(long id) {
		super("No se encontro el heroe con id: "+ id);
	}
}
