package com.ufc.br.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class AulaFileUtils {

	public static void salvarImagem(String caminho, MultipartFile imagem) {
		File file = new File(caminho);
		try {
			FileUtils.writeByteArrayToFile(file, imagem.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void renomearImagem(String nomeAntigo, String novoNome) {
	    try {
			FileUtils.moveFile(
			  FileUtils.getFile(nomeAntigo), 
			  FileUtils.getFile(novoNome));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void subtituirImagem(String caminhoAntigo, String novoCaminho, MultipartFile novaImagem) {
	    try {
	    	FileUtils.touch(new File(caminhoAntigo));
	        File fileToDelete = FileUtils.getFile(caminhoAntigo);
	        FileUtils.deleteQuietly(fileToDelete);
	        
	        File file = new File(novoCaminho);
	        FileUtils.writeByteArrayToFile(file, novaImagem.getBytes());
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deletarImagem(String caminho) {
	    try {
	    	FileUtils.touch(new File(caminho));
	        File fileToDelete = FileUtils.getFile(caminho);
	        FileUtils.deleteQuietly(fileToDelete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
