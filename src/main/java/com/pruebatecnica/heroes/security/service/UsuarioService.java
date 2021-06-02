package com.pruebatecnica.heroes.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.heroes.security.entity.Usuario;
import com.pruebatecnica.heroes.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositoy;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		
		return usuarioRepositoy.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioRepositoy.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepositoy.existsByEmail(email);
	}
	
	public void save(Usuario usuario) {
		usuarioRepositoy.save(usuario);
	}
}
