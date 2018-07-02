package com.carolinachang.contacorrente.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carolinachang.contacorrente.domain.Cliente;
import com.carolinachang.contacorrente.domain.Conta;
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
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<ClienteDTO> findById(@PathVariable String id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ClienteDTO clienteDTO){
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}/contas" ,method=RequestMethod.GET)
	public ResponseEntity<List<Conta>> findContas(@PathVariable String id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(cliente.getContas());
	}

}
