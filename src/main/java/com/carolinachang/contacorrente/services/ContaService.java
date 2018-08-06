package com.carolinachang.contacorrente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.dto.ClienteDTO;
import com.carolinachang.contacorrente.repository.ContaRepository;
import com.carolinachang.contacorrente.services.exception.ObjectNotFoundException;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	public List<Conta> findByClienteId(ClienteDTO clienteId) {
		List<Conta> conta = contaRepository.findByClienteDTO(clienteId);
		return conta;
	}


	public Conta findById(String id) {
		Optional<Conta> conta = contaRepository.findById(id);
		return	conta.orElseThrow(() -> new ObjectNotFoundException(
				"Conta Não encontrado: " +id+ ", Tipo :" +Cliente.class));
	}
	
	public Conta insert(Conta conta) {
		return contaRepository.insert(conta);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		contaRepository.deleteById(id);
	}
	
	public Conta update(Conta conta) {
		Conta newConta = findById(conta.getId());
		updateData(newConta, conta);
		return contaRepository.save(newConta);
		
	}

	private void updateData(Conta newConta, Conta conta) {
		newConta.setNome(conta.getNome());
		newConta.setCiclos(conta.getCiclos());
	}

}
