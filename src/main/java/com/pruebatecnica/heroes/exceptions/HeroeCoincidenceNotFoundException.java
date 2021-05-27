package com.pruebatecnica.heroes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class HeroeCoincidenceNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroeCoincidenceNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new heroe coincidence not found exception.
	 *
	 * @param param the param
	 */
	public HeroeCoincidenceNotFoundException(String param) {
		super("El parametro no se encuentra en ningun heroe: "+ param);
	}
}
