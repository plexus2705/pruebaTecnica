package com.pruebatecnica.heroes.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.heroes.security.entity.Rol;
import com.pruebatecnica.heroes.security.enumeration.RolNombre;
import com.pruebatecnica.heroes.security.repository.RolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	public Optional<Rol> getByRolNombre(RolNombre  rolNombre) {

		return rolRepository.findByRolNombre(rolNombre);
	}

	public void saveRol(Rol rolNombre) {

		 rolRepository.save(rolNombre);
	}
}
