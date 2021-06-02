package com.pruebatecnica.heroes.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pruebatecnica.heroes.security.entity.Rol;
import com.pruebatecnica.heroes.security.enumeration.RolNombre;
import com.pruebatecnica.heroes.security.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner{

	@Autowired
	private RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
		Rol rolUser = new Rol(RolNombre.ROLE_USER);
		
	    rolService.saveRol(rolUser);
		rolService.saveRol(rolAdmin);
	}

}
