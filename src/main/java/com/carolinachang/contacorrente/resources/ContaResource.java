package com.carolinachang.contacorrente.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carolinachang.contacorrente.domain.CicloDePagamento;
import com.carolinachang.contacorrente.domain.Conta;
import com.carolinachang.contacorrente.services.ContaService;

@RestController
@RequestMapping(value="contas")
public class ContaResource {
	
	@Autowired
	private ContaService contaService; 
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public ResponseEntity<List<Conta>> findById(@PathVariable String id){
		List<Conta> contas = contaService.findByClienteId(id);
		return ResponseEntity.ok().body(contas);
	}
	
	@RequestMapping(value="/{id}/ciclos" ,method=RequestMethod.GET)
	public ResponseEntity<List<CicloDePagamento>> findCiclos(@PathVariable String id){
		Conta conta = contaService.findById(id);
		return ResponseEntity.ok().body(conta.getCiclos());
	}
}
