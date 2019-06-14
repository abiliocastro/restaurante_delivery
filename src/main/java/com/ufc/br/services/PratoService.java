package com.ufc.br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.IPratoRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class PratoService {
	@Autowired
	IPratoRepository iPratoRepository;
	
	public void cadastrar(Prato prato, MultipartFile imagem) {
		String caminho = "images/" + prato.getNome() + ".png";
		
		AulaFileUtils.salvarImagem(caminho, imagem);
		
		iPratoRepository.save(prato);
		
	}
	
	public List<Prato> retornarPratos(){
		return iPratoRepository.findAll();
	}

	public void deletar(Long id) {
		iPratoRepository.deleteById(id);
		
	}
}
