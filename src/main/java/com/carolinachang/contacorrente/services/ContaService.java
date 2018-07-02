package com.carolinachang.contacorrente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.repository.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	public List<Conta> findByClienteId(String clienteId) {
		List<Conta> conta = contaRepository.findByClienteId(clienteId);
		return conta;
	}


	public Conta findById(String id) {
		Optional<Conta> conta = contaRepository.findById(id);
		return conta.orElse(null);
	}

}
