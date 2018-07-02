package com.carolinachang.contacorrente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.repository.ClienteRepository;
import com.carolinachang.contacorrente.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(String id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return	cliente.orElseThrow(() -> new ObjectNotFoundException(
					"Cliente Não encontrado: " +id+ ", Tipo :" +Cliente.class));
		
	}

}
