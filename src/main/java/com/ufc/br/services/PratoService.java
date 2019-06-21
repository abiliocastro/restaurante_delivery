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
	
	public void atualizar(Prato prato, MultipartFile imagem) {
		Prato pratoAntigo = iPratoRepository.getOne(prato.getId());
		if(imagem.getOriginalFilename().equals("")) {
			String caminhoAntigo = "images/" + pratoAntigo.getNome() + ".png";
			String caminhoNovo = "images/" + prato.getNome() + ".png";
			AulaFileUtils.renomearImagem(caminhoAntigo, caminhoNovo);
		} else {
			String caminhoAntigo = "images/" + pratoAntigo.getNome() + ".png";
			String caminhoNovo = "images/" + prato.getNome() + ".png";
			AulaFileUtils.subtituirImagem(caminhoAntigo, caminhoNovo, imagem);
		}
		pratoAntigo.setNome(prato.getNome());
		pratoAntigo.setPreco(prato.getPreco());
		iPratoRepository.save(pratoAntigo);
		
	}
	
	public Prato obterPrato(Long id){
		return iPratoRepository.getOne(id);
	}
	
	public List<Prato> retornarPratos(){
		return iPratoRepository.findAll();
	}

	public void deletar(Long id) {
		Prato prato = iPratoRepository.getOne(id);
		String caminho = "images/" + prato.getNome() + ".png";
		AulaFileUtils.deletarImagem(caminho);
		iPratoRepository.deleteById(id);
		
	}
}
