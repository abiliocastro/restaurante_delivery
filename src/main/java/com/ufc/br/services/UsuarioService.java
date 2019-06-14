package com.ufc.br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.IUsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	IUsuarioRepository iUsuarioRepository;
	
	public void cadastrar(Usuario usuario) {
		iUsuarioRepository.save(usuario);
	}
	
	public List<Usuario> retornarUsuarios(){
		return iUsuarioRepository.findAll();
	}

	public void excluir(Long id) {
		iUsuarioRepository.deleteById(id);
	}
	
	public Usuario buscarPorId(Long id) {
		return iUsuarioRepository.getOne(id);
	}
}
