package com.pruebatecnica.heroes.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.heroes.security.entity.Rol;
import com.pruebatecnica.heroes.security.entity.Usuario;
import com.pruebatecnica.heroes.security.enumeration.RolNombre;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	
	public Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
