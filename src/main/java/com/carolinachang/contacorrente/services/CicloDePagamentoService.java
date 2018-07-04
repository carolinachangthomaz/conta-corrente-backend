package com.carolinachang.contacorrente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.dto.ClienteDTO;
import com.carolinachang.contacorrente.repository.CicloDePagamentoRepository;
import com.carolinachang.contacorrente.services.exception.ObjectNotFoundException;

@Service
public class CicloDePagamentoService {
	
	@Autowired
	private CicloDePagamentoRepository cicloDePagamentoRepository;
		
	public CicloDePagamento findById(String id) {
		Optional<CicloDePagamento> CicloDePagamento = cicloDePagamentoRepository.findById(id);
		return	CicloDePagamento.orElseThrow(() -> new ObjectNotFoundException(
				"Ciclo Não encontrado: " +id+ ", Tipo :" +Cliente.class));
	}
	
	public CicloDePagamento insert(CicloDePagamento ciclo) {
		return cicloDePagamentoRepository.insert(ciclo);
	}
	
	public void delete(String id) {
		findById(id);
		cicloDePagamentoRepository.deleteById(id);
	}
	
	public CicloDePagamento update(CicloDePagamento ciclo) {
		CicloDePagamento newCiclo = findById(ciclo.getId());
		updateData(newCiclo, ciclo);
		return cicloDePagamentoRepository.save(newCiclo);
		
	}

	private void updateData(CicloDePagamento newCiclo, CicloDePagamento ciclo) {
		newCiclo.setNome(ciclo.getNome());
		newCiclo.setMes(ciclo.getAno());
		newCiclo.setAno(ciclo.getAno());
		newCiclo.setCreditos(ciclo.getCreditos());
		newCiclo.setDebitos(ciclo.getDebitos());
		
	}

}
