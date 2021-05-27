package com.pruebatecnica.heroes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The Class Heroes.
 */
@Entity
@Table(name = "heroes")
public class Heroes {

 
	  


	/** The id. */
	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private long id;  

	/** The nombre. */
	private String nombre;
	
	

	/**
	 * Instantiates a new heroes.
	 */
	public Heroes() {
		super();
	}



	/**
	 * Instantiates a new heroes.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 */
	public Heroes(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}



	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}



	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	


	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}



	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heroes other = (Heroes) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}



	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Heroes [id=" + id + ", nombre=" + nombre + "]";
	}  
	
	

	

}
