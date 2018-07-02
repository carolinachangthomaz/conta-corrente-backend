package com.carolinachang.contacorrente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.dto.ClienteDTO;
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
	
	public Cliente insert(Cliente cliente) {
		return clienteRepository.insert(cliente);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		clienteRepository.deleteById(id);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = findById(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
		
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setEmail(cliente.getEmail());
		newCliente.setNome(cliente.getNome());
		
	}

}
