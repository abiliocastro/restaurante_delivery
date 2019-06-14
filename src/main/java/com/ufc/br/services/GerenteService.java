package com.ufc.br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Gerente;
import com.ufc.br.repository.IGerenteRepository;

@Service
public class GerenteService {
	@Autowired
	IGerenteRepository iGerenteRepository;
	
	public void cadastrar(Gerente gerente) {
		iGerenteRepository.save(gerente);
	}
	
	public List<Gerente> retornarGerentes(){
		return iGerenteRepository.findAll();
	}

	public void excluir(Long id) {
		iGerenteRepository.deleteById(id);
	}
	
	public Gerente buscarPorId(Long id) {
		return iGerenteRepository.getOne(id);
	}
}
