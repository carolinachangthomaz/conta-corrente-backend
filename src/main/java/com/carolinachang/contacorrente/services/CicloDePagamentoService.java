package com.carolinachang.contacorrente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Cliente;
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

}
