package com.carolinachang.contacorrente.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carolinachang.contacorrente.domain.Cliente;

@RestController
@RequestMapping(value="clientes")
public class ClienteResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll(){
		Cliente cliente = new Cliente("1","Carol","carol.com.jp@gmail.com");
		List<Cliente> clientes = new ArrayList<>();
		clientes.addAll(Arrays.asList(cliente));
		return ResponseEntity.ok().body(clientes);
	}

}
