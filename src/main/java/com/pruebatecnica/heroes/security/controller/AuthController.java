package com.pruebatecnica.heroes.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.heroes.security.dto.JwtDto;
import com.pruebatecnica.heroes.security.dto.LoginUsuario;
import com.pruebatecnica.heroes.security.dto.NuevoUsuario;
import com.pruebatecnica.heroes.security.entity.Rol;
import com.pruebatecnica.heroes.security.entity.Usuario;
import com.pruebatecnica.heroes.security.enumeration.RolNombre;
import com.pruebatecnica.heroes.security.jwt.JwtProvider;
import com.pruebatecnica.heroes.security.service.RolService;
import com.pruebatecnica.heroes.security.service.UsuarioService;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;

	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) 
			return new ResponseEntity("Campos mal puestos",HttpStatus.BAD_REQUEST);
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) 
			return new ResponseEntity("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);	
		if(usuarioService.existsByEmail(nuevoUsuario.getEmail())) 
			return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);
		
		
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(),nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(),
				passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity("Usuario guardado", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) 
			return new ResponseEntity("Campos mal puestos",HttpStatus.BAD_REQUEST);
	
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generatedToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
		
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
}
