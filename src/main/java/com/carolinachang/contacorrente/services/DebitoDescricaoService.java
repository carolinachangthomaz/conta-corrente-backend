package com.carolinachang.contacorrente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.DebitoDescricao;
import com.carolinachang.contacorrente.repository.DebitoDescricaoRepository;
import com.carolinachang.contacorrente.services.exception.ObjectNotFoundException;

@Service
public class DebitoDescricaoService {
	
	@Autowired
	private DebitoDescricaoRepository descricaoRepository;
	
	public List<DebitoDescricao> findAll(){
		return descricaoRepository.findAll();
	}
	
	public DebitoDescricao findById(String id) {
		Optional<DebitoDescricao> cliente = descricaoRepository.findById(id);
		return	cliente.orElseThrow(() -> new ObjectNotFoundException(
					"Descrição Não encontrado: " +id+ ", Tipo :" +DebitoDescricao.class));
		
	}
	
	public DebitoDescricao insert(DebitoDescricao descricao) {
		return descricaoRepository.insert(descricao);
	}
	
	public void delete(String id) {
		findById(id);
		descricaoRepository.deleteById(id);
	}
	
	public DebitoDescricao update(DebitoDescricao descricao) {
		DebitoDescricao newDescricao = findById(descricao.getId());
		updateData(newDescricao, descricao);
		return descricaoRepository.save(newDescricao);
		
	}

	private void updateData(DebitoDescricao newDescricao, DebitoDescricao cliente) {
		newDescricao.setNome(cliente.getNome());
		
	}

}
