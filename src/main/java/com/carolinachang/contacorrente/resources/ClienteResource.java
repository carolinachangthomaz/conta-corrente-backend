package com.carolinachang.contacorrente.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.dto.ClienteDTO;
import com.carolinachang.contacorrente.services.ClienteService;

@RestController
@RequestMapping(value="clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> clientes = clienteService.findAll();
		List<ClienteDTO> clientesDto = clientes.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDto);
	}

}
