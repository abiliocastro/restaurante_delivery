package com.ufc.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Item;
import com.ufc.br.repository.IItemRepository;

@Service
public class ItemService {
	@Autowired
	IItemRepository iItemRepository;
	
	public void cadastrar(Item item) {
		iItemRepository.save(item);
	}
}
